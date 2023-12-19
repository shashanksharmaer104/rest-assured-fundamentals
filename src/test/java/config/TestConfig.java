package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.BeforeClass;

public class TestConfig {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://videogamedb.uk/";
        RestAssured.basePath = "api/v2";
        RestAssured.port = 443;

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType("application/json")
                .addHeader("Accept", "application/json")
                .build();
    }
}
