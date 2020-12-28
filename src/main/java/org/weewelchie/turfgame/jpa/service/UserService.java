package org.weewelchie.turfgame.jpa.service;

import java.util.List;

import org.weewelchie.turfgame.jpa.beans.UserDataBean;

public interface UserService {

    public UserDataBean getUser(String userName);

    public void createUser(UserDataBean user);

    public UserDataBean updateUser(UserDataBean user);

    public List<UserDataBean> findAll();

    
}
