package dev.gabrielsancho.notas.persistence;

import dev.gabrielsancho.notas.dtos.LoginDTO;
import dev.gabrielsancho.notas.logs.LoggerBuilder;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.utils.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDAO {

    public User autentica(LoginDTO loginDTO) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();
        try {

            String jpql = "select u from User u where u.email = :pEmail";

            Query query = em.createQuery(jpql);
            query.setParameter("pEmail", loginDTO.getEmail());
            User queryUser = (User)query.getSingleResult();

            if (!queryUser.checkPassword(loginDTO.getPassword())) {
                throw new Exception("Password does not matck");
            }

            return queryUser;
        }catch (Exception e){
            em.getTransaction().rollback();
            LoggerBuilder.ERROR("Não foi possivel autenticar Usuário", e).log();
            throw (e);
        }finally {
            em.close();
        }
    }



}
