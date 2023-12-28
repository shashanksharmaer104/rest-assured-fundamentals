import config.FootballConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
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

    @Test
    public void extractSingleValueWithFind() {
        Response response = get("/teams/57");
        String singlePlayerName = response.path("squad.find { it.id = 4832 }.name");

        System.out.println("Player name: " + singlePlayerName);
    }

    @Test
    public void extractListOfValuesWithFindAll() {
        Response response = get("/teams/57");
        List<String> listOFPlayers = response.path("squad.findAll { it.id }.name");

        System.out.println("List of players: " + listOFPlayers);
        System.out.println("Size of List of players: " + listOFPlayers.size());
    }
}
