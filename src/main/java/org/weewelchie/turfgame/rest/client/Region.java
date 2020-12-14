package org.weewelchie.turfgame.rest.client;

import java.io.Serializable;

public class Region implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2232683928422697557L;

    private String name = "";
    private Integer id = 0;

    public Region()
    {

    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + "]";
    }

    
    
}