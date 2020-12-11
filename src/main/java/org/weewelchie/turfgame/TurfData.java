package org.weewelchie.turfgame;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/turfgame")
public class TurfData {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello Turfgame";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/user/{userName}")
    public String greeting(@PathParam String userName) {

        //Add call to Turfgame APIs

        return "User Stats for: " + userName;
    }
}