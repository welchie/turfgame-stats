package org.weewelchie.turfgame;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/")
public class HelloRestController {
    
    private static Logger LOGGER = Logger.getLogger(HelloRestController.class.getName());
    private static final String HELLO_TEXT = "Hello World Turfgame Stats";
    
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        LOGGER.info("Returning: :" + HELLO_TEXT);
        return HELLO_TEXT;  
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String rootPage()
    {
        String html = "<html><body><H1>" + HELLO_TEXT + "</H1></body></html>";

        return html;
    }
}