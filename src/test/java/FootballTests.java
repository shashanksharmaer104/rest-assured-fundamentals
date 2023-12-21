import config.FootballConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

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

    @Test
    public void getDateFounded() {
        given()
        .when()
                .pathParams("id", 57)
                .get("/teams/{id}")
        .then()
                .body("founded", equalTo(1886));
    }

    @Test
    public void getFirstTeamName() {
        given()
        .when()
                .pathParams("yearId", "2021")
                .get("/competitions/{yearId}/teams")
        .then()
                .body("teams.name[1]", equalTo("Arsenal FC"));

    }
}
