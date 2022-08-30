package src.main.java.worldcup.scoreboard;

import src.main.java.worldcup.model.Match;

import java.time.Instant;
import java.util.*;

public class ScoreBoardImpl implements ScoreBoard {

    private Map<String, Match> scoreBoard = new HashMap<>();

    @Override
    public Match startNewGame(String homeTeam, String awayTeam) {
        return startNewGame(homeTeam, awayTeam, 0);
    }

    @Override
    public Match startNewGame(String homeTeam, String awayTeam, long timeOffSet) {
        Match match = new Match(
                homeTeam,
                awayTeam,
                0,
                0,
                Instant.now().plusSeconds(timeOffSet)
        );


        System.out.println("New match has been successfully added to the scoreboard, matchId " + match.getMatchId());
        scoreBoard.put(match.getMatchId(), match);
        return match;
    }

    @Override
    public Match updateScore(String matchId, int homeTeamScore, int awayTeamScore) {
        Match match = scoreBoard.get(matchId);
        match.setHomeTeamScore(homeTeamScore);
        match.setAwayTeamScore(awayTeamScore);
        return match;
    }

    @Override
    public List<Match> summary() {
        List<Match> matchList = new ArrayList<>(scoreBoard.values());
        matchList.sort(matchComparator());
        return matchList;
    }

    @Override
    public Match endGame(String matchId) {
        if (matchExists(matchId)) {
            return scoreBoard.remove(matchId);
        } else {
            System.err.println("Match with the following ID  " + matchId + " cannot be found");
        }
        return null;
    }

    private boolean matchExists(String matchId) {
        return scoreBoard.get(matchId) != null;
    }

    private static Comparator<Match> matchComparator() {
        return Comparator.comparing(Match::getTotalScore, Comparator.reverseOrder()).thenComparing(Match::getStartTime, Comparator.reverseOrder());
    }
}
