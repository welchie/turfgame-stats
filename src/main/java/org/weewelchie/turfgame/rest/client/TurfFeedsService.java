package org.weewelchie.turfgame.rest.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

public class TurfFeedsService {
    
@Path("/v4")
@RegisterRestClient(configKey = "turf-api")
public interface TurfUserService {

    @GET
    @Path("/feeds")
    String getFeeds();
}
}
