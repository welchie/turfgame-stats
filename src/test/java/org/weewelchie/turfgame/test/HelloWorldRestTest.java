package org.weewelchie.turfgame.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class HelloWorldRestTest {

private static final String HELLO_TEXT = "Hello Temperture Stats";
private static final String PROJECT_NAME = "turfgame-stats";

@Test
public void testHelloTurfGameEndpoint() {
    given()
        .when().get("/hello")
        .then()
            .statusCode(200)
            .body(is(HELLO_TEXT));
}

@Test
public void testMainEndpoint() {
    given()
        .when().get("/")
        .then()
            .statusCode(200)
            .body(containsString(PROJECT_NAME));
}
    
}

