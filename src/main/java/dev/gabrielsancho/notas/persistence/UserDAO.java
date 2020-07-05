package dev.gabrielsancho.notas.persistence;

import dev.gabrielsancho.notas.dtos.LoginDTO;
import dev.gabrielsancho.notas.dtos.RegisterUserDTO;
import dev.gabrielsancho.notas.logs.LoggerBuilder;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.utils.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDAO {

    public User authenticate(LoginDTO loginDTO) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();
        try {

            String jpql = "select u from User u where u.email = :pEmail";

            Query query = em.createQuery(jpql);
            query.setParameter("pEmail", loginDTO.getEmail());
            User queryUser = (User) query.getSingleResult();

            if (!queryUser.checkPassword(loginDTO.getPassword())) {
                throw new Exception("Password does not match");
            }
            LoggerBuilder.INFO(
                    String.format("Usuário %s de id %d autenticado", queryUser.getName(), queryUser.getId())).log();

            return queryUser;
        } catch (Exception e) {
            em.getTransaction().rollback();
            LoggerBuilder.ERROR("Não foi possivel autenticar Usuário", e).log();
            throw (e);
        } finally {
            em.close();
        }
    }

    public User register(RegisterUserDTO registerUserDTO) throws Exception{
        EntityManager em = HibernateUtils.createEntityManager();


        em.getTransaction().begin();

        try {
            User newUser = new User(registerUserDTO);
            if (!registerUserDTO.passwordMatch()) {
                throw new Exception("Password confirmation do not match");
            }

            em.persist(newUser);
            em.getTransaction().commit();

            LoggerBuilder.INFO(
                    String.format("Usuário %s criado com id %d", newUser.getName(), newUser.getId())).log();
            return newUser;

        } catch (Exception e) {
            em.getTransaction().rollback();
            LoggerBuilder.ERROR("Não foi possível criar Usuário", e).log();
            throw(e);
        } finally {
            em.close();
        }
    }

    public User getUserById(Long id)  throws Exception{
        EntityManager em = HibernateUtils.createEntityManager();

        try {
            return em.find(User.class, id);
        } catch (Exception e) {
            LoggerBuilder.ERROR(String.format("Não foi possível encontrar usuário de id %d", id), e).log();
            em.getTransaction().rollback();
            throw(e);
        } finally {
            em.close();
        }
    }
}
