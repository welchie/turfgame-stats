package org.weewelchie.turfgame.jpa.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.weewelchie.turfgame.rest.client.UserData;

@ApplicationScoped
public class UserDataService implements UserService {

    @Inject
    private EntityManager em;

    @Override
    public UserData getUser(String userName) {

        UserData user;

        Query query = em.createNamedQuery("UserData.findUserByName");
        query.setParameter("userName", userName);
        user = (UserData)query.getSingleResult();
        
        return user;

    }

    @Transactional
    public void createUser(UserData user) {
        if (user != null) {
            if (!user.getName().equals("")) {
                // Persiste data
                em.persist(user);
                em.flush();
            }

        }

    }

    @Override
    public UserData updateUser(UserData user) {
        em.persist(user);
        em.flush();

        return this.getUser(user.getName());
    }

    @Override
    public List<UserData> findAll() {
        return em.createNamedQuery("UserData.findAll", UserData.class).getResultList();
    }

}
