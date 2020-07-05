package dev.gabrielsancho.notas.services;

import com.google.gson.Gson;
import dev.gabrielsancho.notas.dtos.LoginDTO;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.persistence.UserDAO;
import dev.gabrielsancho.notas.security.TokenSecurity;
import dev.gabrielsancho.notas.utils.HibernateUtils;

import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;

public class UserService {

    public final UserDAO dao = new UserDAO();

    public User autentica(LoginDTO loginDTO) throws Exception {
        return dao.autentica(loginDTO);
    }

    public User registra(JsonObject userJSON) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();

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
        EntityManager em = HibernateUtils.createEntityManager();
        List<String> id = headers.getRequestHeader("id");
        return em.find(User.class, Long.parseLong(id.get(0)));
    }

    public User getLoggedUser(HttpServletRequest servletRequest) throws Exception {
        EntityManager em = HibernateUtils.createEntityManager();
        String id = TokenSecurity.validateJwtToken(servletRequest.getHeader("authorization"));
        return em.find(User.class, Long.parseLong(id));
    }
}
