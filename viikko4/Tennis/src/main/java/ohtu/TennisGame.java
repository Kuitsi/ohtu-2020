package ohtu;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {

    private int player1score = 0;
    private int player2score = 0;
    private final String player1Name;
    private final String player2Name;
    private final Map<Integer, String> scoreNames = new HashMap<>();

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        scoreNames.put(0, "Love");
        scoreNames.put(1, "Fifteen");
        scoreNames.put(2, "Thirty");
        scoreNames.put(3, "Forty");
    }

    public void wonPoint(String playerName) throws IllegalArgumentException {
        if (playerName.equalsIgnoreCase(player1Name)) {
            player1score += 1;
        } else if (playerName.equalsIgnoreCase(player2Name)) {
            player2score += 1;
        } else {
            throw new IllegalArgumentException("Name must be " + player1Name + " or " + player2Name);
        }
    }

    private String getEvenScore() {
        if (scoreNames.containsKey(player1score)) {
            return scoreNames.get(player1score) + "-All";
        } else {
            return "Deuce";
        }
    }

    private String getMidgameScore() {
        return scoreNames.get(player1score) + "-" + scoreNames.get(player2score);
    }

    public String getScore() {
        if (player1score == player2score) {
            return getEvenScore();
        }

        boolean isExtraPoints = player1score >= 4 || player2score >= 4;
        if (isExtraPoints) {
            int pointsDifference = player1score - player2score;
            if (Math.abs(pointsDifference) >= 2) {
                return "Win for " + (player1score > player2score ? player1Name : player2Name);
            } else {
                return "Advantage " + (player1score > player2score ? player1Name : player2Name);
            }
        }

        return getMidgameScore();
    }
}
