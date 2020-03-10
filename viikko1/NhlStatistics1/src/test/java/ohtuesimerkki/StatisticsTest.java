package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Semenko", "EDM", 4, 12)); //16 points
            players.add(new Player("Lemieux", "PIT", 45, 54));//99
            players.add(new Player("Kurri",   "EDM", 37, 53));//90
            players.add(new Player("Yzerman", "DET", 42, 56));//98
            players.add(new Player("Gretzky", "EDM", 35, 89));//124
            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchFound() {
        Player p = stats.search("Kurri");
        assertNotNull(p);
        assertEquals("Kurri", p.getName());
    }

    @Test
    public void searchPartialFound() {
        Player p = stats.search("tzk");
        assertNotNull(p);
        assertEquals("Gretzky", p.getName());
    }

    @Test
    public void searchNotFound() {
        Player p = stats.search("Aku Ankka");
        assertNull(p);
    }

    @Test
    public void teamUnknown() {
        List<Player> players = stats.team("ASD");
        assertNotNull(players);
        assertEquals(0, players.size());
    }

    @Test
    public void teamPit() {
        List<Player> players = stats.team("PIT");
        assertEquals(1, players.size());
        Player p = players.get(0);
        assertEquals("Lemieux", p.getName());
        assertEquals("PIT", p.getTeam());
        assertEquals(45, p.getGoals());
        assertEquals(54, p.getAssists());
    }

    @Test
    public void teamEdm() {
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
    }

    @Test
    public void topScorersTwo() {
        List<Player> players = stats.topScorers(2);
        assertEquals(2, players.size());

        assertEquals("Gretzky", players.get(0).getName());
        assertEquals(124, players.get(0).getPoints());
        assertEquals(35, players.get(0).getGoals());
        assertEquals(89, players.get(0).getAssists());

        assertEquals("Lemieux", players.get(1).getName());
        assertEquals(99, players.get(1).getPoints());
    }

    @Test
    public void topScorersMoreThanPlayers() {
        List<Player> players = stats.topScorers(10);
        assertEquals(5, players.size());
        assertEquals("Gretzky", players.get(0).getName());
        assertEquals("Semenko", players.get(4).getName());
    }

    @Test
    public void topScorersNegative() {
        List<Player> players = stats.topScorers(-1);
        assertEquals(0, players.size());
    }
}