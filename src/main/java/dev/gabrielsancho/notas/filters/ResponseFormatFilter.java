package dev.gabrielsancho.notas.filters;

import dev.gabrielsancho.notas.dtos.ResponseDTO;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

public class ResponseFormatFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        if (request.getMethod().equals("OPTIONS"))
            return;

        // TODO: Adicionar tratamento para entity null

        ResponseDTO responseDTO = new ResponseDTO(response.getStatus(), response.getEntity());

        response.setEntity(responseDTO);
    }
}
