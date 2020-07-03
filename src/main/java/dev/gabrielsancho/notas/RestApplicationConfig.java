package dev.gabrielsancho.notas;

import dev.gabrielsancho.notas.filters.AuthenticationFilter;
import dev.gabrielsancho.notas.filters.CorsFilter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

//@ApplicationPath("api")
public class RestApplicationConfig extends ResourceConfig {

    public RestApplicationConfig() {
        packages("dev.gabrielsancho.notas.resources");
        register(AuthenticationFilter.class);
        register(CorsFilter.class);
    }
}
