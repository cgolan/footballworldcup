package src.test.java.worldcup.scoreboard;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardTest {

    @Test
    void should_start_a_new_game(){
        //Given
        ScoreBoard scoreBoard = new ScoreBoardImpl();
        scoreBoard.startNewGame("Mexico", "Canada");

        //When
        List<Match> matchList = scoreBoard.summary();

        //Then
        assertFalse(matchList.isEmpty);
    }

    @Test
    void should_finish_game_in_progress(){
        //Given
        ScoreBoard scoreBoard = new ScoreBoardImpl();
        Match match = scoreBoard.startNewGame("Mexico", "Canada");

        //When
        scoreBoard.endGame(match.getMatchId());

        //Then
        List<Match> matchList = scoreBoard.summary();
        assertTrue(matchList.isEmpty);
    }

    @Test
    void should_update_score(){
        //Given
        ScoreBoard scoreBoard = new ScoreBoardImpl();
        Match match = scoreBoard.startNewGame("Mexico", "Canada");

        int awayTeamOldScore = match.getAwayTeamScore();

        //When
        Match updatedMatch = scoreBoard.updateScore(match.getMatchId(), 0, 5);
        int awayTeamNewScore = updatedMatch.getAwayTeamScore();

        //Then
        List<Match> matchList = scoreBoard.summary();
        assertFalse(matchList.isEmpty);
        assertNotSame(awayTeamOldScore,awayTeamNewScore);
    }

    @Test
    void should_display_summary_of_games_ordered_by_total_score_and_by_most_recently_started_game(){
        //Given
        ScoreBoard scoreBoard = new ScoreBoardImpl();

        Match mexicoVsCanada = scoreBoard.newGame("Mexico", "Canada");
        Match spainVsBrazil = scoreBoard.newGame("Spain", "Brazil");
        Match germanyVsFrance = scoreBoard.newGame("Germany", "France");
        Match uruguayVsItaly = scoreBoard.newGame("Uruguay", "Italy", 10);
        Match argentinaVsFrance = scoreBoard.newGame("Argentina", "Australia");

        scoreBoard.updateScore(mexicoVsCanada.getMatchId(), 0, 5);
        scoreBoard.updateScore(spainVsBrazil.getMatchId(), 10, 2);
        scoreBoard.updateScore(germanyVsFrance.getMatchId(), 2, 2);
        scoreBoard.updateScore(uruguayVsItaly.getMatchId(), 6, 6);
        scoreBoard.updateScore(argentinaVsFrance.getMatchId(), 3, 1);

        //When
        List<Match> matchList = scoreBoard.summary();
        assertFalse(matchList.isEmpty);
        assertEquals(matchList.get(0).getMatchId, uruguayVsItaly.getMatchId());

    }
}
