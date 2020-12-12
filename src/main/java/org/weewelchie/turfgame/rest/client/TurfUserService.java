package org.weewelchie.turfgame.rest.client;

import java.util.Set;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v2")
@RegisterRestClient

public interface TurfUserService {

    @POST
    @Path("/user/{name}")
    Set<TurfData> getByName(@PathParam String name);
}