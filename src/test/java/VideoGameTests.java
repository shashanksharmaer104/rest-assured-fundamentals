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
}
