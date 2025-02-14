package com.nttdata.controlleradvice;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class GenericControllerAdvice {

    @ServerExceptionMapper(value = RuntimeException.class)
    public Response handleIOException(RuntimeException exception) {
        return Response.serverError().entity(exception.getMessage()).build();
    }
}
