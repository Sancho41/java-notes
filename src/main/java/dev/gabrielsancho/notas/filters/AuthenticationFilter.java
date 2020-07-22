package dev.gabrielsancho.notas.filters;

import dev.gabrielsancho.notas.security.TokenSecurity;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * The AuthenticationFilter verifies the access permissions for a user based on the provided jwt token
 * and role annotations
 **/
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    public static final String HEADER_PROPERTY_ID = "id";
    public static final String AUTHORIZATION_PROPERTY = "authorization";

    // Do not use static responses, rebuild reponses every time
    private static final String ACCESS_REFRESH = "Token expired. Please authenticate again!";
    private static final String ACCESS_INVALID_TOKEN = "Token invalid. Please authenticate again!";
    private static final String ACCESS_DENIED = "Not allowed to access this resource!";
    private static final String ACCESS_FORBIDDEN = "Access forbidden!";
    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();
        // everybody can access (e.g. user/create or user/authenticate)


        if (requestContext.getMethod().equals("OPTIONS"))
            return;


        if (!method.isAnnotationPresent(PermitAll.class)) {
            // nobody can access
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).entity(ACCESS_FORBIDDEN).build()
                );
                return;
            }

            // get request headers to extract jwt token
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            final List<String> authProperty = headers.get(AUTHORIZATION_PROPERTY);

            // block access if no authorization information is provided
            if (authProperty == null || authProperty.isEmpty()) {
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).entity(ACCESS_DENIED).build()
                );

                return;
            }

            String id = null;
            String bearer = authProperty.get(0);

            // try to decode the bearer - deny access if no valid token provided
            try {
                id = TokenSecurity.validateJwtToken(bearer);

            } catch (Exception e) {
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).entity(ACCESS_INVALID_TOKEN).build()
                );
            }

            List<String> idList = new ArrayList<String>();
            idList.add( id );
            headers.put( HEADER_PROPERTY_ID, idList );
        }
    }
}
