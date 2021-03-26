package org.weewelchie.temperature;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.weewelchie.temperature.monitor.jpa.bean.TemperatureDataBean;

@ApplicationScoped
@Path("/temperature")
public class TemperatureRestController {

    private static final String HELLO_TEMPERATURE = "Hello Temperature Stats";
    private static Logger LOGGER = Logger.getLogger(TemperatureRestController.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("Returning: " + HELLO_TEMPERATURE);
        return HELLO_TEMPERATURE;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TemperatureDataBean> getAllData() {

        LOGGER.info("Getting all Data.");
        List<TemperatureDataBean> allData = new ArrayList<TemperatureDataBean>();
        List<TemperatureDataBean> findAllData = TemperatureDataBean.findAllData();
        for (TemperatureDataBean t : findAllData) {
            allData.add(t);
        }
        return allData;
    }

    @GET
    @Path("/findByDeviceName/{deviceName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TemperatureDataBean> getAllDataByDeviceName(@PathParam("deviceName") String deviceName) {

        LOGGER.info("Getting all Data by deviceName: " + deviceName);
        List<TemperatureDataBean> allData = new ArrayList<TemperatureDataBean>();
        List<TemperatureDataBean> findAllData = TemperatureDataBean.findByDeviceName(deviceName);
        for (TemperatureDataBean t : findAllData) {
            allData.add(t);
        }
        return allData;
    }

    @GET
    @Path("/all/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getCount() {
        return TemperatureDataBean.findAllData().size();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public TemperatureDataBean addTemperatureData(TemperatureDataBean dataBean) {
        dataBean.timeStamp = LocalDateTime.now();
        LOGGER.info("DataBean: " + dataBean);
        dataBean.persist();
        return dataBean;
    }

    @DELETE
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteTermperatureData(TemperatureDataBean dataBean) {
        if (dataBean == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }

        TemperatureDataBean t = TemperatureDataBean.findById(dataBean.id);
        if (t == null) {
            return Response.status(Status.NOT_FOUND).build();
        } else {
            LOGGER.info("Deleting Temperature Data: " + t);
            TemperatureDataBean.deleteById(t.id);
        }

        return Response.ok().build();
    }

}