package dev.gabrielsancho.notas.resources;

import dev.gabrielsancho.notas.dtos.NoteDTO;
import dev.gabrielsancho.notas.model.Note;
import dev.gabrielsancho.notas.model.User;
import dev.gabrielsancho.notas.services.NoteService;
import dev.gabrielsancho.notas.services.UserService;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.json.JSONObject;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("notes")
@RequestScoped
public class NoteResource {

    private static final NoteService service = new NoteService();
    private static final UserService userService = new UserService();

    @Context
    ResourceInfo headers;

    @Context
    HttpServletRequest servletRequest;

    @Context
    HttpServletResponse servletResponse;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(@Context HttpHeaders headers) {
        try {
            User loggedUser = userService.getLoggedUser(headers);
            return Response.ok(service.userNotes(loggedUser)).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response store(@Context HttpHeaders headers, NoteDTO noteDTO) {
        try {
            User loggedUser = userService.getLoggedUser(headers);
            return Response.ok(service.create(noteDTO, loggedUser)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"bad_request\"}").build();
        }
    }

    @GET
    @PermitAll
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam("id") Long id) {
        try {
            try {
                User loggedUser = userService.getLoggedUser(servletRequest);
                return Response.ok(service.getNoteById(id, loggedUser)).build();
            } catch (Exception e) {
                return Response.ok(service.getNoteById(id)).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"not_found\"}")
                    .build();
        }
    }

    @PATCH
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Context HttpHeaders headers, @PathParam("id") Long id, NoteDTO note) throws Exception {
        try {
            User loggedUser = userService.getLoggedUser(headers);
            return Response.ok(service.updateNote(note, loggedUser)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@Context HttpHeaders headers, @PathParam("id") Long id) {
        try {
            User loggedUser = userService.getLoggedUser(headers);
            service.deleteNote(id, loggedUser);
            return Response.ok("{\"success\": \"deleted\"}").build();
        } catch (Exception e) {
            JSONObject object = new JSONObject();
            object.put("error", "unauthorized");
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(object)
                    .build();
        }
    }

    @GET
    @PermitAll
    @Path("public")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response publicNotes(
            @Context HttpHeaders headers,
            @QueryParam("per_page") Long perPage,
            @QueryParam("page") Long page) {
        try {
            User user = userService.getLoggedUser(servletRequest);
            return Response.ok(service.publicNotes(user, page, perPage)).build();
        } catch (Exception e ) {
            return Response.ok(service.publicNotes(page, perPage)).build();
        }
    }

    @POST
    @Path("favorite/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response favoriteNote(@Context HttpHeaders headers, @PathParam("id") Long id) {
        try {
            User user = userService.getLoggedUser(headers);
            return Response.ok(service.favorite(user, id)).build();
        } catch (Exception e) {
            JSONObject object = new JSONObject();
            object.put("error", "unauthorized");
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(object)
                    .build();
        }
    }

    @GET
    @Path("favorites")
    @Produces(MediaType.APPLICATION_JSON)
    public Response favorites(@Context HttpHeaders headers) {
        try {
            User user = userService.getLoggedUser(headers);
            return Response.ok().entity(service.favorites(user)).build();
        } catch (Exception e) {
            JSONObject object = new JSONObject();
            object.put("error", "unauthorized");
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(object)
                    .build();
        }
    }
}
