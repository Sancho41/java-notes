package dev.gabrielsancho.notas.persistence;

import dev.gabrielsancho.notas.logs.LoggerBuilder;
import dev.gabrielsancho.notas.model.Note;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.utils.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class NoteDAO {

    public List<Note> getUserNotes(User user) {
        EntityManager em = HibernateUtils.createEntityManager();

        try {
            String jpql = "Select n from Note n where n.user = :pUser";
            TypedQuery<Note> query = em.createQuery(jpql, Note.class);
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

    public Note getNoteById(Long id) {
        EntityManager em = HibernateUtils.createEntityManager();
        try {
            return em.find(Note.class, id);
        } catch (Exception e){
            LoggerBuilder.ERROR(String.format("Não foi possível recuperar nota de id %d", id), e).log();
            em.getTransaction().rollback();
            throw (e);
        } finally {
            em.close();
        }

    }

    public Note update(Note note, User user) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();


        try {
            String jpql = "Select n from Note n where n = :pNote and n.user = :pUser";

            Query query = em.createQuery(jpql);
            query.setParameter("pNote", note);
            query.setParameter("pUser", user);
            query.getSingleResult();

            note.setUser(user);

            em.getTransaction().begin();
            em.merge(note);
            em.getTransaction().commit();

            return note;
        } catch (Exception e) {
            LoggerBuilder.ERROR(
                    String.format("Não foi possível alterar a nota de id %d", note.getId()), e).log();
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
}