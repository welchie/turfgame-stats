package org.weewelchie.turfgame.test.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.weewelchie.turfgame.jpa.beans.UserDataBean;
import org.weewelchie.turfgame.rest.client.UserData;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserDataBeanTest {
    
    private UserDataBean user = new UserDataBean();
    private static final String USER_NAME = "testUser";
    @BeforeAll
    public void setUp()
    {
        user.setBlocktime(123456);
        user.setCountry("uk");
        user.setId(9999);
        user.setMedals("[1,2,3,4,5,6,7,8,9]");
        user.setName(USER_NAME);
        user.setPlace(987654321);
        user.setPoints(10000);
        user.setRank(1);
        user.setRegion("Scoutland");
        user.setTaken(88);
        user.setTotalPoints(999999);
        user.setUniqueZonesTaken(15);
        user.setUserID(99L);
        user.setZones("[5,6,7,8,4,2]");
    }

    @Test
    public void testToDomain()
    {
        UserData userData = UserDataBean.toDomain(user);
        assertEquals(USER_NAME,userData.getName());
    }

    @Test
    public void testToBean()
    {
        UserData userData = UserDataBean.toDomain(user);
        UserDataBean userDataBean = UserDataBean.toBean(userData);
        assertEquals(USER_NAME,userDataBean.getName());
    }
}
