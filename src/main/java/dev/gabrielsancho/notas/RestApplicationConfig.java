package dev.gabrielsancho.notas;

import dev.gabrielsancho.notas.filters.AuthenticationFilter;
import dev.gabrielsancho.notas.filters.CorsFilter;
import dev.gabrielsancho.notas.filters.ResponseFormatFilter;
import org.glassfish.jersey.server.ResourceConfig;

//@ApplicationPath("api")
public class RestApplicationConfig extends ResourceConfig {

    public RestApplicationConfig() {
        packages("dev.gabrielsancho.notas.resources");
        register(AuthenticationFilter.class);
        register(CorsFilter.class);
        register(ResponseFormatFilter.class);
    }
}
