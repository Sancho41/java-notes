package dev.gabrielsancho.notas.services;

import com.google.gson.Gson;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.security.TokenSecurity;

import javax.json.JsonObject;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;

public class UserService {

    private EntityManager createEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("notas");
        return emf.createEntityManager();
    }

    public User autentica(JsonObject userAtempt) throws Exception {

        EntityManager em = createEntityManager();

        String jpql = "select u from User u where u.email = :pEmail";

        Query query = em.createQuery(jpql);
        query.setParameter("pEmail", userAtempt.getString("email"));
        User queryUser = (User)query.getSingleResult();

        if (queryUser.checkPassword(userAtempt.getString("password"))) {
            return queryUser;
        }
        throw new Exception("Login failed exception");
    }

    public User registra(JsonObject userJSON) throws Exception {
        EntityManager em = createEntityManager();

        String password = userJSON.getString("password");
        String password_confirmation = userJSON.getString("password_confirmation");

        if (password.equals(password_confirmation)) {
            Gson gson = new Gson();
            User user = gson.fromJson(userJSON.toString(), User.class);

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();

            return user;
        }
        throw new Exception("Register failed exception");
    }

    public User getLoggedUser(HttpHeaders headers) throws Exception {
        EntityManager em = createEntityManager();
        List<String> id = headers.getRequestHeader("id");
        return em.find(User.class, Long.parseLong(id.get(0)));
    }

    public User getLoggedUser(HttpServletRequest servletRequest) throws Exception {
        EntityManager em = createEntityManager();
        String id = TokenSecurity.validateJwtToken(servletRequest.getHeader("authorization"));
        return em.find(User.class, Long.parseLong(id));
    }
}
