package dev.gabrielsancho.notas.persistence;

import dev.gabrielsancho.notas.dtos.NoteDTO;
import dev.gabrielsancho.notas.dtos.NoteListDTO;
import dev.gabrielsancho.notas.logs.LoggerBuilder;
import dev.gabrielsancho.notas.model.Note;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.utils.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class NoteDAO {

    public List<NoteDTO> getUserNotes(User user) {
        EntityManager em = HibernateUtils.createEntityManager();

        try {
            String jpql = "Select new dev.gabrielsancho.notas.dtos.NoteDTO(";
            jpql += "n,";
            jpql += "exists(select 1 from User u where n member of u.notesFavorited and u = :pUser)";
            jpql += ")from Note n ";
            jpql += "where n.user = :pUser";

            TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
            query.setParameter("pUser", user);

            return query.getResultList();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LoggerBuilder.ERROR(
                    String.format("Não foi possível recuperar as notas do usuário de id %d", user.getId()), e).log();
            throw (e);
        } finally {
            em.close();
        }


    }

    public Note create(Note note)  throws Exception{
        EntityManager em = HibernateUtils.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(note);
            em.getTransaction().commit();
            return note;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LoggerBuilder.ERROR("Não foi possível criar a nota", e).log();
            throw (e);
        } finally {
            em.close();
        }
    }

    public NoteDTO getNoteById(Long id) {
        EntityManager em = HibernateUtils.createEntityManager();
        try {
            String jpql = "select new dev.gabrielsancho.notas.dtos.NoteDTO(n) from Note n where n.id = :pNoteId and n.is_public = true";
            TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
            query.setParameter("pNoteId", id);
            return query.getSingleResult();
        } catch (Exception e){
            LoggerBuilder.ERROR(String.format("Não foi possível recuperar nota de id %d", id), e).log();
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }
    }

    public NoteDTO getNoteById(Long id, User user) {
        EntityManager em = HibernateUtils.createEntityManager();
        try {
            String jpql = "select new dev.gabrielsancho.notas.dtos.NoteDTO(";
            jpql += "n,";
            jpql += "exists(select 1 from User u where n member of u.notesFavorited and u = :pUser)";
            jpql += ") from Note n where n.id = :pNoteId and (n.is_public = true or n.user = :pUser)";
            TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
            query.setParameter("pNoteId", id);
            query.setParameter("pUser", user);
            return query.getSingleResult();
        } catch (Exception e){
            LoggerBuilder.ERROR(String.format("Não foi possível recuperar nota de id %d", id), e).log();
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }
    }

    public NoteDTO update(NoteDTO noteDTO, User user) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();


        try {
            String jpql = "Select n from Note n where n.id = :pNoteId and n.user = :pUser";

            Query query = em.createQuery(jpql);
            query.setParameter("pNoteId", noteDTO.getId());
            query.setParameter("pUser", user);
            Note note = (Note) query.getSingleResult();

            note.setUser(user);

            note.updateFromDTO(noteDTO);

            em.getTransaction().begin();
            em.merge(note);
            em.getTransaction().commit();

            return noteDTO;
        } catch (Exception e) {
            LoggerBuilder.ERROR(
                    String.format("Não foi possível alterar a nota de id %d", noteDTO.getId()), e).log();
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }
    }

    public void delete(Long id, User user)  throws Exception{
        EntityManager em = HibernateUtils.createEntityManager();

        try {
            String jpql = "Select n from Note n where n.id = :pId and n.user = :pUser";

            Query query = em.createQuery(jpql);
            query.setParameter("pId", id);
            query.setParameter("pUser", user);
            Note note = (Note) query.getSingleResult();

            em.getTransaction().begin();
            em.remove(note);
            em.getTransaction().commit();
        } catch (Exception e) {
            LoggerBuilder.ERROR(
                    String.format("Não foi possível deletar a nota de id %d", id), e).log();
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }
    }

    public NoteListDTO getPublicNotes(Long page, Long perPage) {
        EntityManager em = HibernateUtils.createEntityManager();
        try {

            Long qtd = (Long) em.createQuery(
                    "Select count(*) from Note n where n.is_public = true").getSingleResult();

            perPage = perPage == null || perPage < 0 ? qtd : perPage;
            page = page == null || page < 0 ? 0 : page - 1;

            Long lastPage = Math.round(Math.ceil(qtd.doubleValue() / perPage.doubleValue()));
            Long firstResult = page * perPage;

            String jpql = "Select new dev.gabrielsancho.notas.dtos.NoteDTO(n) from Note n where n.is_public = true";
            TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
            query.setMaxResults(perPage.intValue());
            query.setFirstResult(firstResult.intValue());

            List<NoteDTO> notes = query.getResultList();

            return new NoteListDTO(page + 1, lastPage, notes);
        } catch (Exception e) {
            LoggerBuilder.ERROR("Não foi possível recupar as notas públicas", e).log();
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }
    }

    public NoteListDTO getPublicNotes(User user, Long page, Long perPage) {
        EntityManager em = HibernateUtils.createEntityManager();
        try {
            String jpql = "Select %s from Note n where n.is_public = true";

            String select1 = "count(*)";
            String select2 = "new dev.gabrielsancho.notas.dtos.NoteDTO(";
            select2 += "n, exists(select 1 from User u where n member of u.notesFavorited and u = :pUser))";

            Long qtd = em.createQuery(String.format(jpql, select1), Long.class).getSingleResult();

            perPage = perPage == null || perPage < 0 ? qtd : perPage;
            page = page == null || page < 0 ? 0 : page - 1;

            Long lastPage = Math.round(Math.ceil(qtd.doubleValue() / perPage.doubleValue()));
            Long firstResult = page * perPage;

            TypedQuery<NoteDTO> query = em.createQuery(String.format(jpql, select2), NoteDTO.class);
            query.setParameter("pUser", user);
            query.setMaxResults(perPage.intValue());
            query.setFirstResult(firstResult.intValue());

            List<NoteDTO> notes = query.getResultList();

            return new NoteListDTO(page + 1, lastPage, notes);
        } catch (Exception e) {
            LoggerBuilder.ERROR("Não foi possível recupar as notas públicas", e).log();
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }
    }

    public NoteDTO favoriteNote(Long id, User user) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();
        try {

            Note note = em.find(Note.class, id);

            if (!(note.isIs_public() || note.getUser().equals(user))) {
                throw new Exception("Não autorizado");
            }

            user = em.find(User.class, user.getId());
            user.toggleFavorite(note);

            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();

            return getNoteById(id, user);
        } catch (Exception e) {
            LoggerBuilder.ERROR(
                    String.format("Não foi possível favoritar a nota de id %d com usuário de id %d", id, user.getId()),
                    e).log();
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }
    }
}