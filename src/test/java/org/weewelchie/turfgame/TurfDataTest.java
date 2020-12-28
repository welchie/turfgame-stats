package org.weewelchie.turfgame;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TurfDataTest {

    @Test
    public void testHelloTurfGameEndpoint() {
        given()
          .when().get("/turfgame")
          .then()
             .statusCode(200)
             .body(is("Hello Turfgame"));
    }

}