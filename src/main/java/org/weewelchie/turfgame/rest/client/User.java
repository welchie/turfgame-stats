package org.weewelchie.turfgame.rest.client;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class User {

    private String name;

    public User(String name)
    {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }

    public String toJSON() {
        return String.format("[{\"name\":\"%s\"}]",
                             this.name);

    }    

}