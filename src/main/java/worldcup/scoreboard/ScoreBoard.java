package src.main.java.worldcup.scoreboard;

import src.main.java.worldcup.model.Match;

import java.util.List;

public interface ScoreBoard {
    Match startNewGame(String mexico, String canada);
    Match startNewGame(String mexico, String canada, long timeOffSet);

    Match updateScore(String matchId, int homeTeamScore, int awayTeamScore);

    List<Match> summary();

    Match endGame(String matchId);
}
