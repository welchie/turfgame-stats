package org.weewelchie.turfgame.test.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.weewelchie.turfgame.jpa.beans.UserDataBean;
import org.weewelchie.turfgame.jpa.service.UserService;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Inject
	public
    UserService userService;

    private UserDataBean testUser = null;
    private UserDataBean testUser1 = null;
    private UserDataBean testUser2 = null;

    private String userName = "welchie99";
    private static int NUM_USERS = 10;
    private static String TEST_USER_NAME = "userName_";

    @BeforeAll
    public void setUp() {
        testUser = new UserDataBean();
        testUser.setName(userName);

        // Create some dummy Users
        for (int i = 0; i < 10; i++) {
            UserDataBean u = new UserDataBean();
            u.setName(TEST_USER_NAME + i);
            userService.createUser(u);
        }

    }

    @Test
    public void testCreateUser() {
        userService.createUser(testUser);
        UserDataBean u = userService.getUser(userName);
        assertEquals(u.getName(), testUser.getName());
    }

    @Test
    public void testFindAll() {
        List<UserDataBean> users = userService.findAll();
        assertTrue(users.size() >= NUM_USERS);
        for (UserDataBean u : users) {
            assertTrue(u.getName().contains(TEST_USER_NAME));
        }
    }

    @Test
    public void testAddDuplicateUser() {
        testUser1 = new UserDataBean();
        testUser1.setName(userName);
        Assertions.assertThrows(PersistenceException.class, () -> {
            userService.createUser(testUser1);
        }
        );
        UserDataBean u1 = userService.getUser(userName);
        assertEquals(u1.getName(), testUser.getName());

        testUser2 = new UserDataBean();
        testUser2.setName(userName);
        Assertions.assertThrows(PersistenceException.class, () -> {
            userService.createUser(testUser2);
        });
    }

    @Test
    public void testRemoveUser()
    {
        UserDataBean user = new UserDataBean();
        user.setName(TEST_USER_NAME);
        userService.createUser(user);

        UserDataBean u = userService.getUser(TEST_USER_NAME);
        assertTrue(u != null);

        userService.removeUser(TEST_USER_NAME);
        Assertions.assertThrows(PersistenceException.class, () -> {
            userService.getUser(TEST_USER_NAME);
        });
       
        
    }
}
