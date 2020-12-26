package org.weewelchie.turfgame.jpa.service;

import java.util.List;

import org.weewelchie.turfgame.rest.client.UserData;

public interface UserService {

    public UserData getUser(String userName);

    public void createUser(UserData user);

    public UserData updateUser(UserData user);

    public List<UserData> findAll();

    
}
