package dev.gabrielsancho.notas.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter, ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        if (requestContext.getHeaderString("Origin") == null) {
            return;
        }

        if (requestFromOriginOptions(requestContext)) {
            responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            responseContext.getHeaders().add("Access-Control-Allow-Headers",
                    "X-Requested-With, Authorization, X-Powered-By," + "Accept-Version, Content-MD5, CSRF-Token, Content-Type");
        }

        String origin = "http://localhost:3000";
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);
        responseContext.getHeaders().add("Access-Control-Expose-Headers", "Content-type, Content-Disposition, X-Suggested-Filename");
    }

    @Override
    public void filter(ContainerRequestContext req) throws IOException {
        if (requestFromOriginOptions(req)) {
            req.abortWith(Response.ok().build());
        }
    }

    public static Boolean requestFromOriginOptions(ContainerRequestContext req) {
        return req.getHeaderString("Origin") != null && req.getMethod().equalsIgnoreCase("OPTIONS");
    }
}
