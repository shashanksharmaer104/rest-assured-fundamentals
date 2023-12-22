import config.VideoGameConfig;
import config.VideoGameEndpoints;
import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import objects.VideoGame;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class VideoGameTests extends VideoGameConfig {

    //Test Swagger URL = https://www.videogamedb.uk/swagger-ui/index.html#/api-video-games-controller-v-2/

    String gameJSONBody = "{\n" +
            "  \"category\": \"Platform\",\n" +
            "  \"name\": \"God of war 4\",\n" +
            "  \"rating\": \"Universal\",\n" +
            "  \"releaseDate\": \"2018-05-04\",\n" +
            "  \"reviewScore\": 95\n" +
            "}";

    String gameXMLBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<VideoGameRequest>\n" +
            "\t<category>Platform</category>\n" +
            "\t<name>RDR2</name>\n" +
            "\t<rating>Mature</rating>\n" +
            "\t<releaseDate>2016-05-04</releaseDate>\n" +
            "\t<reviewScore>85</reviewScore>\n" +
            "</VideoGameRequest>";

    @Test
    public void getVideoGames() {
        given()
        .when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void createNewGameWithJSON() {
        given()
                .body(gameJSONBody)
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void createNewGameWithXML() {
        given()
                .body(gameXMLBody)
                .contentType("application/xml") // Overriding base content type value
                .accept("application/json") // Overriding base content type value
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void updateGameByJSON() {
        given()
                .body(gameJSONBody)
        .when()
                .put("/videogame/2")
        .then();
    }

    @Test
    public void updateGameByXML() {
        given()
                .body(gameXMLBody)
                .header("Content-Type", "application/xml")
                .header("Accept", "application/xml")
        .when()
                .put("/videogame/3")
        .then();
    }

    @Test
    public void deleteGame() {
        given()
                .header("Accept", "text/plain")
        .when()
                .delete("/videogame/2")
        .then();
    }

    @Test
    public void getSingleGame() {
        given()
                .pathParams("videoGameId", 1)
        .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
        .then();
    }

    @Test
    public void testVideoGameSerializationByJSON() {
        VideoGame videoGame = new VideoGame("Action", "Call of Duty - Modern Warfare",
                "Mature", "2017-12-10", 97);
        given()
                .body(videoGame)
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void testVideoGameSchemaXML() {
        given()
                .accept("application/xml")
        .when()
                .pathParams("videoGameId", 8)
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
        .then()
                .body(RestAssuredMatchers.matchesXsdInClasspath("VideoGameXSD.xsd"));
    }

    @Test
    public void testVideoGameSchemaJSON() {
        given()
                .accept("application/json")
        .when()
                .pathParams("videoGameId", 8)
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
        .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("VideoGameJSONSchema.json"));
    }

    @Test
    public void convertJsonToPojo() {
        Response response =
                given()
                    .accept("application/json")
                .when()
                    .pathParams("videoGameId", 5)
                    .get(VideoGameEndpoints.SINGLE_VIDEO_GAME);

        VideoGame videoGame = response.getBody().as(VideoGame.class);

        System.out.println(videoGame.toString());

        System.out.println(response.asString());

        System.out.printf("Name: %s%n", videoGame.getName());
    }

    @Test
    public void checkResponseTime() {
        long responseTime = get(VideoGameEndpoints.ALL_VIDEO_GAMES).time();

        System.out.println("Response time in MS: " + responseTime);
    }

    @Test
    public void assertResponseTime() {
        get(VideoGameEndpoints.ALL_VIDEO_GAMES).then()
                .time(lessThan(1500L));
    }
}
