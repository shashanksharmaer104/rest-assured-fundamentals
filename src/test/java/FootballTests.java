import config.FootballConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FootballTests extends FootballConfig {

    @Test
    public void getDetailsOfAllAreas() {
        given()
        .when()
                .get("/areas")
        .then();
    }

    @Test
    public void getDetailsOfOneArea() {
        given()
                .queryParam("areas", 2000)
        .when()
                .get("/areas")
        .then();
    }

    @Test
    public void getDetailsOfMultipleArea() {
        String areaIds = "2000,2001,2002";
        given()
                .urlEncodingEnabled(false)
                .queryParam("areas", areaIds)
        .when()
                .get("/areas")
        .then();
    }
}
