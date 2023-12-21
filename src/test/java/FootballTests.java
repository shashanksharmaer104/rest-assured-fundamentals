import config.FootballConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.get;
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

    @Test
    public void getAllTeamData() {
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllTeamData_DoCheckFirst() {
        Response response =
                given()
                .when()
                        .get("teams/57")
                .then()
                        .contentType(ContentType.JSON) // first check if response type is JSON
                        .extract().response();

        System.out.println(response.asString());
    }

    @Test
    public void extractHeaders() {
        Response response =
                get("/teams/57")
                .then()
                        .extract().response();

        System.out.println("My type: " + response.getContentType());
        System.out.println("My header: " + response.getHeader("X-Authenticated-Client"));
    }
}
