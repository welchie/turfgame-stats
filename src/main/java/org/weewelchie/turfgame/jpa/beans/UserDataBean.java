package org.weewelchie.turfgame.jpa.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.weewelchie.turfgame.rest.client.Region;
import org.weewelchie.turfgame.rest.client.UserData;



@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Entity
@Table(name="user_data")
@NamedQueries({
     @NamedQuery(name = "UserDataBean.findUserByName", query = "SELECT u FROM UserDataBean u WHERE u.name = :userName"),
     @NamedQuery(name = "UserDataBean.findAll", query = "SELECT u FROM UserDataBean u ORDER BY u.name")
})
public class UserDataBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6913960696200724172L;
    private static Logger LOGGER = Logger.getLogger(UserDataBean.class.getName());

    @Id
    @GeneratedValue
    private Long userID;

    @Column(name="user_name", nullable=false, unique = true)
    String name;

    @Column(name="country")
    String country;

    @Column(name="medals")
    String medals;

    @Column(name="zones")
    String zones;

    @Column(name="points_per_hour")
    Integer pointsPerHour = 0;

    @Column(name="points")
    Integer points = 0;

    @Column(name="total_points")
    Integer totalPoints = 0;

    @Column(name="rank")
    Integer rank =0;

    @Column(name="id")
    Integer id = 0;

    @Column(name="place")
    Integer place = 0;

    @Column(name="unique_zones_taken")
    Integer uniqueZonesTaken = 0;

    @Column(name="region")
    String region;

    @Column(name="block_time")
    Integer blocktime = 0;

    @Column(name="taken")
    Integer taken= 0;

    public UserDataBean()
    {

    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getPointsPerHour() {
        return pointsPerHour;
    }

    public void setPointsPerHour(Integer pointsPerHour) {
        this.pointsPerHour = pointsPerHour;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getUniqueZonesTaken() {
        return uniqueZonesTaken;
    }

    public void setUniqueZonesTaken(Integer uniqueZonesTaken) {
        this.uniqueZonesTaken = uniqueZonesTaken;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(Integer blocktime) {
        this.blocktime = blocktime;
    }

    public Integer getTaken() {
        return taken;
    }

    public void setTaken(Integer taken) {
        this.taken = taken;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getMedals() {
        return medals;
    }

    public void setMedals(String medals) {
        this.medals = medals;
    }

    public String getZones() {
        return zones;
    }

    public void setZones(String zones) {
        this.zones = zones;
    }

    @Override
    public String toString() {
        return "UserDataBean [blocktime=" + blocktime + ", country=" + country + ", id=" + id 
                + ", name=" + name + ", place=" + place + ", points=" + points + ", pointsPerHour=" + pointsPerHour
                + ", rank=" + rank + ", region=" + region + ", taken=" + taken + ", totalPoints=" + totalPoints
                + ", uniqueZonesTaken=" + uniqueZonesTaken + ", userID=" + userID + ", userMedals=" + medals
                + ", userZones=" + zones + "]";
    }


	/**
     * Converts JPA Entitiy to domain bean
     */
    public static UserData toDomain(UserDataBean user)
    {
        UserData userData = new UserData();

        userData.setBlocktime(user.getBlocktime());
        userData.setCountry(user.getCountry());
        userData.setId(user.getId());
        userData.setName(user.getName());

        Region region = new Region();
        region.setId(user.getId());
        region.setName(user.getRegion());
        userData.setRegion(region);

        userData.setPlace(user.getPlace());
        userData.setPoints(user.getPoints());
        userData.setPointsPerHour(user.getPointsPerHour());
        userData.setRank(user.getRank());
        userData.setTaken(user.getTaken());
        userData.setTotalPoints(user.getTotalPoints());
        userData.setUniqueZonesTaken(user.getUniqueZonesTaken());

        //Convert Medals data
        String medals = user.getMedals();
        medals = medals.substring(1,medals.length()-1);
        String[] medalsArr = medals.split(",");
        LOGGER.info("Medals: " + medals);
        LOGGER.info("Array: " + Arrays.toString(medalsArr));
        List<Integer> medalsList = new ArrayList<Integer>();
        for(String s:medalsArr)
        {
            if (s != "")
            {
                medalsList.add(Integer.parseInt(s.trim()));
            }
        }
        userData.setMedals(medalsList);

        //Convert Zones data
        String zones = user.getZones();
        zones = zones.substring(1,zones.length()-1);
        String[] zonesArr = zones.split(",");
        LOGGER.info("Zones: " + zones);
        LOGGER.info("Array: " + Arrays.toString(zonesArr));
        List<Integer> zonesList = new ArrayList<Integer>();
        for(String s:zonesArr)
        {
            if (s!= "")
            {
                zonesList.add(Integer.parseInt(s.trim()));
            }
        }
        userData.setZones(zonesList);

        return userData;
    }

    /**
     * Convert to Bean
     */
    public static UserDataBean toBean(UserData user)
    {
        UserDataBean userData = new UserDataBean();
        userData.setBlocktime(user.getBlocktime());
        userData.setCountry(user.getCountry());
        userData.setId(user.getId());
        userData.setMedals(user.getMedals().toString());
        userData.setName(user.getName());
        userData.setPlace(user.getPlace());
        userData.setPoints(user.getPoints());
        userData.setPointsPerHour(user.getPointsPerHour());
        userData.setRank(user.getRank());
        userData.setRegion(user.getRegion().getName());
        userData.setTaken(user.getTaken());
        userData.setTotalPoints(user.getTotalPoints());
        userData.setUniqueZonesTaken(user.getUniqueZonesTaken());
        userData.setZones(user.getZones().toString());

        return userData;
    }
   
    
    
    
}