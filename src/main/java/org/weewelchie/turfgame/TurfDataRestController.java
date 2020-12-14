package org.weewelchie.turfgame;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.weewelchie.turfgame.rest.client.UserData;
import org.weewelchie.turfgame.rest.client.User;

@Path("/turfgame")
public class TurfDataRestController {

    private static Logger LOGGER = Logger.getLogger(TurfDataRestController.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello Turfgame";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/user/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserData getUser(@PathParam String userName) {

        // Add call to Turfgame APIs
        // curl -X POST -d '[{"name":"welchie99"}]' https//api.turfgame.com/v4/users/

        User user = new User(userName);
        LOGGER.info("User: " + user.toJSON());

        LOGGER.info("JSON: " + Entity.json(user));
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.turfgame.com/v4/users/");
        String response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(user.toJSON()), String.class);

        UserData turfData = null;
        try {
            String resp = response.substring(1, response.length()-1);
            LOGGER.fine("Resp: " +resp);
            turfData = new ObjectMapper().readValue(resp, UserData.class);

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        LOGGER.info("Retrieved User data: " + turfData);

        return turfData;


    }
}