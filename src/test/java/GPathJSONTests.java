import config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends FootballConfig {

    @Test
    public void extractMapOfElements() {
        Response response = get("/competitions/2021/teams");

        Map<String, ?> allDetailsOfSingleTeam = response.path("teams.find { it.name == 'Manchester United FC' }");

        System.out.println("Map of data: " + allDetailsOfSingleTeam);

        for(Map.Entry<String, ?> entry : allDetailsOfSingleTeam.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

}
