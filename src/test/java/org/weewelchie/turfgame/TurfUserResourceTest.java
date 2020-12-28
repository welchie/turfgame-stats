package org.weewelchie.turfgame;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TurfUserResourceTest {
    private static final String USER_NAME = "welchie99";

    @Test
    public void testGetUserPoint() {
        given()
            .when().get("/turfgame/user/" + USER_NAME)
            .then()
            .statusCode(200);
            
            
            //.body("$.size()", is(1), additionalKeyMatcherPairs)
            //.body("$.size()", is(1),"[0].country" , is("uk")
            //);
    }
}
