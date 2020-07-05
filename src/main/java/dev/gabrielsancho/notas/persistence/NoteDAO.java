package dev.gabrielsancho.notas.persistence;

import dev.gabrielsancho.notas.logs.LoggerBuilder;
import dev.gabrielsancho.notas.model.Note;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.utils.HibernateUtils;

import javax.persistence.EntityManager;
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
                    String.format("Não foi possível recuperar as notas do usuário de id %d", user.getId()) ,e).log();
            throw(e);
        } finally {
            em.close();
        }


    }
}