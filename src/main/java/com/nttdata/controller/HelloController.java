package com.nttdata.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Path("/")
@Slf4j
public class HelloController {

    @GET
    public Response helloWorld() {
        log.atInfo().log("helloWorld has been called");
        return Response.ok().entity("Hello World!").build();
    }

    //docker run --name practice-docker -p 8080:8080 --mount type=volume,src=practice,dst=/practice practice-docker:1.0.0-SNAPSHOT
    // NOTE: This demonstrates, that whenever we create a volume, add it to the container and then write to that volume (dir),
    // the volume will preserve it's state even after the container has been deleted
    @Path("/volume")
    @GET
    public Response volumeEndpoint() {
        log.atInfo().log("volumeEndpoint has been called");
        try {
            PrintWriter writer = new PrintWriter("practice/practice_volume.txt", StandardCharsets.UTF_8);
            writer.println("Volume");
            writer.close();
            return Response.ok().entity(FileUtils.readFileToString(new File("practice/practice_volume.txt"), StandardCharsets.UTF_8)).build();
        } catch (IOException e) {
            throw new RuntimeException("File is not existent", e);
        }
    }

    //docker run --name practice-docker -p 8080:8080 --mount type=bind,src=C:\Users\Zoltan\Desktop\practice_bind.txt,dst=/practice_bind.txt practice-docker:1.0.0-SNAPSHOT
    // NOTE: Try changing the file content on the host machine (not in the docker image)
    @Path("/bind")
    @GET
    public Response bindEndpoint() {
        log.atInfo().log("bindEndpoint has been called");
        try {
            return Response.ok().entity(FileUtils.readFileToString(new File("practice_bind.txt"), StandardCharsets.UTF_8)).build();
        } catch (IOException e) {
            throw new RuntimeException("File is not existent", e);
        }
    }

    //docker run --name practice-docker -p 8080:8080 --mount type=tmpfs,dst=/practice practice-docker:1.0.0-SNAPSHOT
    // NOTE: The volume will only be persistent in the container. Once the container is deleted, the volume disappears with it
    @Path("/tmpfs")
    @GET
    public Response tmpfsEndpoint() {
        log.atInfo().log("tmpfsEndpoint has been called");
        try {
            PrintWriter writer = new PrintWriter("practice/practice_tmpfs.txt", StandardCharsets.UTF_8);
            writer.println("tmpfs");
            writer.close();
            return Response.ok().entity(FileUtils.readFileToString(new File("practice/practice_tmpfs.txt"), StandardCharsets.UTF_8)).build();
        } catch (IOException e) {
            throw new RuntimeException("File is not existent", e);
        }
    }
}
