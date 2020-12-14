package org.weewelchie.turfgame.rest.client;

import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/turfgame")
public class TurfUserResource {

    private final static Logger LOGGER = Logger.getLogger(TurfUserResource.class.getName());


    @Inject
    @RestClient
    TurfUserService turfUserService;

    @POST
    @Path("/users/{name}") 
    @Produces("application/json")
    public Set<String> name(@PathParam String name) {
        LOGGER.info("getByName:" +name);
    
        return turfUserService.getByName(name);
    }
    
}
