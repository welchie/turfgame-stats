package org.weewelchie.turfgame.rest.client;

import java.util.Set;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v4")
@RegisterRestClient(configKey = "turf-api")
public interface TurfUserService {

    @POST
    @Path("/users/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Set<String> getByName(@PathParam String name);
}