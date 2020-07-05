package dev.gabrielsancho.notas.resources;

import dev.gabrielsancho.notas.dtos.LoginDTO;
import dev.gabrielsancho.notas.dtos.TokenDTO;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.security.TokenSecurity;
import dev.gabrielsancho.notas.services.UserService;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.annotation.security.PermitAll;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
@RequestScoped
public class AuthResource {

    private static final UserService service = new UserService();

    @Context
    ResourceInfo headers;

    @Context
    HttpServletRequest servletRequest;

    @Context
    HttpServletResponse servletResponse;

    @POST
    @PermitAll
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        try {
            User user = service.autentica(loginDTO);
            return Response.ok(
                    new TokenDTO(
                            TokenSecurity
                                    .generateJwtToken(Long.toString(user.getId()))
                    )
            ).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity("login failed").build();
        }
    }

    @POST
    @PermitAll
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(JsonObject userJSON) {
        try {
            User user = service.registra(userJSON);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("register failed").build();
        }
    }

    @POST
    @PermitAll
    @Path("logout")
    public String logout(User user) {
        return servletRequest.getHeader("Authorization");
    }

    @GET
    @Path("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response user(@Context HttpHeaders headers) {
        try {
            User loggedUser = service.getLoggedUser(headers);
            return Response.ok(loggedUser).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Not authenticated").build();
        }
    }
}