package org.weewelchie.turfgame.rest.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Entity(name="user_data")
public class UserData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6913960696200724172L;

    @Id
    private Long userID;

    @Column(name="user_name", nullable=false, unique = true)
    String name;

    @Column(name="country")
    String country;

    @Column(name="medals")
    List<Integer> medals = new ArrayList<Integer>();

    @Column(name="zones")
    List<Integer> zones = new ArrayList<Integer>();

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
    Region region;

    @Column(name="block_time")
    Integer blocktime = 0;

    @Column(name="taken")
    Integer taken= 0;

    public UserData()
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

    public List<Integer> getMedals() {
        return medals;
    }

    public void setMedals(List<Integer> medals) {
        this.medals = medals;
    }

    public List<Integer> getZones() {
        return zones;
    }

    public void setZones(List<Integer> zones) {
        this.zones = zones;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
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

    @Override
    public String toString() {
        return "UserData [blocktime=" + blocktime + ", country=" + country + ", id=" + id + ", medals=" + medals
                + ", name=" + name + ", place=" + place + ", points=" + points + ", pointsPerHour=" + pointsPerHour
                + ", rank=" + rank + ", region=" + region + ", taken=" + taken + ", totalPoints=" + totalPoints
                + ", uniqueZonesTaken=" + uniqueZonesTaken + ", zones=" + zones + "]";
    }

    @Id
    @SequenceGenerator(name = "userIDSeq", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "userID")
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

   
    
    
    
}