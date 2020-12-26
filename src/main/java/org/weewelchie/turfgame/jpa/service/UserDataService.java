package org.weewelchie.turfgame.jpa.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.weewelchie.turfgame.rest.client.UserData;

@ApplicationScoped
public class UserDataService implements UserService {

    @Inject
    private EntityManager em;

    @Override
    public UserData getUser(String userName) {

        List<UserData> userResults;
        userResults = em.createNamedQuery("FROM UserData s WHERE u.name= :userName", UserData.class)
                .setParameter("userName", userName).getResultList();

        return userResults.get(0);

    }

    @Override
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
