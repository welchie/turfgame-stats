package org.weewelchie.turfgame.jpa.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.weewelchie.turfgame.jpa.beans.UserDataBean;



@ApplicationScoped
public class UserDataService implements UserService {

    @Inject
    private EntityManager em;

    @Override
    public UserDataBean getUser(String userName) {

        UserDataBean user;

        Query query = em.createNamedQuery("UserDataBean.findUserByName");
        query.setParameter("userName", userName);
        user = (UserDataBean)query.getSingleResult();
        
        return user;

    }

    @Transactional
    public void createUser(UserDataBean user) {
        if (user != null) {
            if (!user.getName().equals("")) {
                // Persiste data
                em.persist(user);
                em.flush();
            }

        }

    }

    @Override
    public UserDataBean updateUser(UserDataBean user) {
        em.persist(user);
        em.flush();

        return this.getUser(user.getName());
    }

    @Override
    public List<UserDataBean> findAll() {
        return em.createNamedQuery("UserDataBean.findAll", UserDataBean.class).getResultList();
    }

}
