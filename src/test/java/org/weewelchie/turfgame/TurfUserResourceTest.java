package org.weewelchie.turfgame;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TurfUserResourceTest {
    
    @Test
    public void testUserNameEndPoint() {
        given()
            .when().post("/users")
            .then()
            .statusCode(200)
            .body("$.size()", is(1),
                    "[0].country" , is("uk")
            );
    }
}
