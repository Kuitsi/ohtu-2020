package ohtu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        List<Player> players = mapper.fromJson(bodyText, new TypeToken<ArrayList<Player>>(){}.getType());
        players.sort(new Comparator<Player>() {
            // decreasing total points
            @Override
            public int compare(Player p1, Player p2) {
                int p1TotalPoints = p1.getGoals() + p1.getAssists();
                int p2TotalPoints = p2.getGoals() + p2.getAssists();
                if (p1TotalPoints == p2TotalPoints) {
                    return 0;
                }
                return p1TotalPoints > p2TotalPoints ? -1 : 1;
             }
        });

        System.out.println("Players from FIN:");
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                System.out.println(player);
            }
        }
    }

}