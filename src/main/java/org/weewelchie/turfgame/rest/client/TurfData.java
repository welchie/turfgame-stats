package org.weewelchie.turfgame.rest.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurfData {

    String name;
    String country;
    List<Integer> medals = new ArrayList<Integer>();
    List<Integer> zones = new ArrayList<Integer>();
    Integer points = 0;
    Integer totalPoints = 0;

    
}