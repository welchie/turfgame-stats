package org.weewelchie.turfgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    @Produces(MediaType.APPLICATION_JSON)
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
        //Check if User Exists
        try
        {
            UserDataBean u = userService.getUser(userDataBean.getName());
            //Update User
            userDataBean.setUserID(u.getUserID());
            LOGGER.info("Updating User: " + userDataBean.getName());
            userService.updateUser(userDataBean);
        }
        catch (NoResultException e)
        {
            //No enitity create a new one
            //Create new User
            LOGGER.info("Creating a new User: " + userDataBean.getName());
            userService.createUser(userDataBean);
        }
        
        return userData;

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/addall/{users}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<UserData> addAll(@PathParam String users) {

        //List<String> usrList = new ArrayList<String>();
        String array1[]= users.split(",");
        List<String> usrList = Arrays.asList(array1);
        LOGGER.info("Users Received: " + usrList);
        LOGGER.info("Users data count: " + usrList.size());
        //LOGGER.info("Users received: " + users);
        List<UserData> usersList = new ArrayList<UserData>();
        for(String userName:usrList)
        {
            UserData usr = getUser(userName);
            usersList.add(usr);
        }
        
        return usersList;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/find/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findUser(@PathParam String userName) 
    {
        UserData u  = new UserData();
        try
        {
            u =UserDataBean.toDomain(userService.getUser(userName));
            return Response.ok(u).build();
        }
        catch (javax.persistence.NoResultException e)
        {
            LOGGER.severe("User: " + userName + " Not Found!");
            return Response.noContent().build();
        }        
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam String userName) 
    {
        try
        {
            userService.removeUser(userName);
            return Response.ok().build();
        }
        catch (javax.persistence.NoResultException e)
        {
            LOGGER.severe("User: " + userName + " Not Found!");
            return Response.noContent().build();
        }        
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/feeds")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getFeeds() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URL + "/v4/feeds");
        Builder request = target.request(MediaType.APPLICATION_JSON);
        Response response = request.buildGet().invoke();
        LOGGER.info("Response Status: " + response.getStatus());
        LOGGER.info("Media Type: " + response.getMediaType());
        return response;
    }


    @GET
    @Path("users/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserData> getAllUsers() {

        List<UserData> usersList = new ArrayList<UserData>();
        List<UserDataBean> users = userService.findAll();
        for(UserDataBean u:users)
        {
            usersList.add(UserDataBean.toDomain(u));
        }
        
        return usersList;  
    }
}