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

    @Test
    public void extractSingleValueWithHighestNumber() {
        Response response = get("/teams/57");
        String name = response.path("squad.max { it.id }.name");
        System.out.println("Highest player name: " + name);
    }

    @Test
    public void extractSingleValueWithLowestNumber() {
        Response response = get("/teams/57");
        String name = response.path("squad.min { it.id }.name");
        System.out.println("Lowest player name: " + name);
    }

    @Test
    public void extractSumOfNumbers() {
        Response response = get("/teams/57");
        int sum = response.path("squad.collect { it.id }.sum()");
        System.out.println("Sum of number: " + sum);
    }

    @Test
    public void extractMapOfPlayerWithParams() {
        String position = "Offence";
        String nationality = "England";
        Response response = get("/teams/57");
        Map<String, ?> map = response.path("squad.findAll { it.position = '%s' }.find { it.nationality = '%s' }", position, nationality);
        System.out.println("Map of player: " + map);
    }

    @Test
    public void extractMapOfPlayersWithParams() {
        String position = "Offence";
        String nationality = "England";
        Response response = get("/teams/57");
        List<Map<String, ?>> listMap = response.path("squad.findAll { it.position = '%s' }.findAll { it.nationality = '%s' }", position, nationality);
        System.out.println("Map of players: " + listMap);
        System.out.println("Size of Map of players: " + listMap.size());
    }

    @Test
    public void extractListOfPlayersWithParams() {
        String position = "Offence";
        String nationality = "England";
        Response response = get("/teams/57");
        List<String> players = response.path("squad.findAll { it.position = '%s' }.findAll { it.nationality = '%s' }.name", position, nationality);
        System.out.println("Size of List of players: " + players.size());
    }
}
