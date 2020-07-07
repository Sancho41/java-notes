package dev.gabrielsancho.notas.persistence;

import dev.gabrielsancho.notas.dtos.NoteDTO;
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

    public List<Note> getPublicNotes() {
        EntityManager em = HibernateUtils.createEntityManager();
        try {
            String jpql = "Select n from Note n where n.is_public = true";
            TypedQuery<Note> query = em.createQuery(jpql, Note.class);
            query.setMaxResults(10);
            return query.getResultList();
        } catch (Exception e) {
            LoggerBuilder.ERROR("Não foi possível recupar as notas públicas", e).log();
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }
    }

    public List<NoteDTO> getPublicNotes(User user) {
        EntityManager em = HibernateUtils.createEntityManager();
        try {
            String jpql = "Select new dev.gabrielsancho.notas.dtos.NoteDTO(";
            jpql += "n,";
            jpql += "exists(select 1 from User u where n member of u.notesFavorited and u = :pUser)";
            jpql += ")from Note n ";
            jpql += "where n.is_public = true";

            TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
            query.setParameter("pUser", user);
            query.setMaxResults(10);
            return query.getResultList();
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