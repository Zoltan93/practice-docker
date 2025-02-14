package com.nttdata.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/")
@Slf4j
public class HelloController {

    @GET
    public Response helloWorld() {
        log.atInfo().log("helloWorld has been called");
        return Response.ok().entity("Hello World!").build();
    }
}
