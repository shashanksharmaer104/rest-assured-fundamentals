import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameTests extends VideoGameConfig {

    @Test
    public void getVideoGames() {
        given()
        .when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void createNewGameWithJSON() {
        String gameJSONBody = "{\n" +
                "  \"category\": \"Platform\",\n" +
                "  \"name\": \"God of war 4\",\n" +
                "  \"rating\": \"Mature\",\n" +
                "  \"releaseDate\": \"2018-05-04\",\n" +
                "  \"reviewScore\": 95\n" +
                "}";

        given()
                .body(gameJSONBody)
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }

    @Test
    public void createNewGameWithXML() {
        String gameXMLBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<VideoGameRequest>\n" +
                "\t<category>Platform</category>\n" +
                "\t<name>RDR2</name>\n" +
                "\t<rating>Mature</rating>\n" +
                "\t<releaseDate>2016-05-04</releaseDate>\n" +
                "\t<reviewScore>85</reviewScore>\n" +
                "</VideoGameRequest>";

        given()
                .body(gameXMLBody)
                .contentType("application/xml") // Overriding base content type value
                .accept("application/json") // Overriding base content type value
        .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
        .then();
    }
}
