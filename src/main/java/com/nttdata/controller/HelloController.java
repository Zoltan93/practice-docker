package com.nttdata.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;

@Slf4j
@Path("/")
public class HelloController {

    @GET
    public String helloWorld() {
        log.atInfo().log("helloWorld has been called");
        return "Hello World!";
    }

    @Path("/load")
    @GET
    public String volumeEndpoint() {
        log.atInfo().log("volumeEndpoint has been called");
        return "Hello World!";
    }
}
