package org.weewelchie.turfgame;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.json.JSONObject;
import org.weewelchie.turfgame.jpa.beans.UserDataBean;
import org.weewelchie.turfgame.jpa.service.UserService;
import org.weewelchie.turfgame.rest.client.User;
import org.weewelchie.turfgame.rest.client.UserData;

@ApplicationScoped
@Path("/turfgame")
public class TurfDataRestController {

    private static Logger LOGGER = Logger.getLogger(TurfDataRestController.class.getName());
    private static String BASE_URL = "https://api.turfgame.com/";

    @Inject
    UserService userService;

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
        WebTarget target = client.target(BASE_URL + "/v4/users/");
        String response = target.request(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN_TYPE)
                .post(Entity.json(user.toJSON()), String.class);

        UserData userData = null;
        try {
            String resp = response.substring(1, response.length() - 1);
            LOGGER.fine("Resp: " + resp);
            userData = new ObjectMapper().readValue(resp, UserData.class);

        } catch (JsonProcessingException e) {
            LOGGER.severe("JsonProcessingException: " + e.getMessage());
        }

        LOGGER.info("Retrieved User data: " + userData);

        LOGGER.info("Convert to UserDataBen entitiy and store in DB");
        UserDataBean userDataBean = UserDataBean.toBean(userData);
        LOGGER.info("userDataBean: " + userDataBean);
        //userService.createUser(userDataBean);
        // Call service layer to store data in DB

        return userData;

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/feeds")
    @Consumes(MediaType.APPLICATION_JSON)
    public String getFeeds() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URL + "/v4/feeds");
        Builder request = target.request(MediaType.APPLICATION_JSON);
        Response response = request.buildGet().invoke();
        // Response response =
        // target.request(MediaType.APPLICATION_JSON).accept(MediaType.TEXT_PLAIN_TYPE).get();
        LOGGER.info("Response Status: " + response.getStatus());
        LOGGER.info("Response: " + response.getEntity().toString());

        LOGGER.info("EntrySet: " + response.getMetadata().entrySet().toString());

        LOGGER.info("Entity JSON: " + Entity.json(response.getEntity()).toString());

        JSONObject jsonObj = new JSONObject(response.getEntity());

        LOGGER.info("JSON Object: " + jsonObj);
        return response.toString();
    }


    //@GET
    //@Path("users/all")
    //public List<UserData> getAllUsers() {
    //    return userService.findAll();   
    //}
}