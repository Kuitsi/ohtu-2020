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

    public String getScore() {
        if (player1score == player2score) {
            return getEvenScore();
        }

        String score = "";
        int tempScore = 0;
        if (player1score >= 4 || player2score >= 4) {
            int minusResult = player1score - player2score;
            if (minusResult == 1) {
                score = "Advantage " + player1Name;
            } else if (minusResult == -1) {
                score = "Advantage " + player2Name;
            } else if (minusResult >= 2) {
                score = "Win for " + player1Name;
            } else {
                score = "Win for " + player2Name;
            }
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1) {
                    tempScore = player1score;
                } else {
                    score += "-";
                    tempScore = player2score;
                }
                switch (tempScore) {
                    case 0:
                        score += "Love";
                        break;
                    case 1:
                        score += "Fifteen";
                        break;
                    case 2:
                        score += "Thirty";
                        break;
                    case 3:
                        score += "Forty";
                        break;
                }
            }
        }
        return score;
    }
}
