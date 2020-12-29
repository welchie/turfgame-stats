package org.weewelchie.turfgame.jpa.service;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.weewelchie.turfgame.jpa.beans.UserDataBean;



@ApplicationScoped
public class UserDataService implements UserService {

    private static Logger LOGGER = Logger.getLogger(UserDataService.class.getName());

    @Inject
    private EntityManager em;

    @Override
    public UserDataBean getUser(String userName) {
        LOGGER.info("Getting User: " + userName);
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
                LOGGER.info("Creating User: " + user.getName());
                em.persist(user);
                em.flush();
            }

        }

    }

    @Transactional
    public UserDataBean updateUser(UserDataBean user) {
        LOGGER.info("Updating User: " + user.getName() + " ID: " + user.getUserID());
        Session session = em.unwrap(Session.class);
        session.saveOrUpdate(user);
        em.flush();

        return this.getUser(user.getName());
    }

    @Override
    public List<UserDataBean> findAll() {
        return em.createNamedQuery("UserDataBean.findAll", UserDataBean.class).getResultList();
    }

    @Transactional
    public void removeUser(String userName) {
        UserDataBean user = getUser(userName);
        if (user != null)
        {
            LOGGER.info("Removing User: " + user.getName() + " ID: " + user.getUserID());
            em.remove(user);
            em.flush();
        }
    }

}
