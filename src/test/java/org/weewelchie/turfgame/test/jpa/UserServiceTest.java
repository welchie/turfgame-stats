package org.weewelchie.turfgame.test.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.h2.engine.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.weewelchie.turfgame.jpa.service.UserService;
import org.weewelchie.turfgame.rest.client.UserData;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Inject 
    UserService userService;

    private UserData testUser = null;
    private String userName = "welchie99";
    private List<UserData> testUsers= new ArrayList<UserData>();
    private static int NUM_USERS = 10;
    private static String TEST_USER_NAME = "userName_";

   
    @BeforeAll
    public void setUp()
    {
        testUser = new UserData();
        testUser.setName(userName);

        //Create some dummy Users
        for (int i=0;i<10;i++)
        {
            UserData u =new UserData();
            u.setName(TEST_USER_NAME + i);
            userService.createUser(u);
        }

    }
    
    @Test
    public void TestCreateUser()
    {
        userService.createUser(testUser);
        UserData u = userService.getUser(userName);
        assertEquals(u.getName(),testUser.getName());
    }

    @Test
    public void testFindAll()
    {
        List<UserData> users = userService.findAll();
        assertTrue(users.size() >= NUM_USERS);
        for (UserData u:users)
        {
            assertTrue(u.getName().contains(TEST_USER_NAME));
        }
    }
}
