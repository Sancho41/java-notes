package dev.gabrielsancho.notas.services;

import dev.gabrielsancho.notas.dtos.LoginDTO;
import dev.gabrielsancho.notas.dtos.RegisterUserDTO;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.persistence.UserDAO;
import dev.gabrielsancho.notas.security.TokenSecurity;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

public class UserService {

    public final UserDAO dao = new UserDAO();

    public User autentica(LoginDTO loginDTO) throws Exception {
        return dao.authenticate(loginDTO);
    }

    public User registra(RegisterUserDTO registerUserDTO) throws Exception {
        return dao.register(registerUserDTO);
    }


    public User getLoggedUser(HttpHeaders headers) throws Exception {
        Long id = Long.parseLong(headers.getRequestHeader("id").get(0));
        return dao.getUserById(id);
    }

    public User getLoggedUser(HttpServletRequest servletRequest) throws Exception {
        String id = TokenSecurity.validateJwtToken(servletRequest.getHeader("Authorization"));
        return dao.getUserById(Long.parseLong(id));
    }
}
