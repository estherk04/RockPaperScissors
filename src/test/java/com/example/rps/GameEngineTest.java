package com.example.rps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {

    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        gameEngine = new GameEngine("Tester");
    }

    @Test
    void testWinScenario() {
        // ROCK beats SCISSORS
        GameResult result = gameEngine.playRound(Move.ROCK);
        assertNotNull(result);
        assertEquals("Tester", result.getPlayerName());
        assertEquals(Move.ROCK, result.getPlayerMove());
        assertNotNull(result.getComputerMove());
        assertNotNull(result.getResult());
    }

    @Test
    void testGetScoreAfterOneGame() {
        gameEngine.playRound(Move.PAPER);
        var score = gameEngine.getScore();
        assertTrue(score.containsKey("Tester"));
        assertTrue(score.containsKey("Computer"));
        assertTrue(score.containsKey("Ties"));
    }

    @Test
    void testMultipleRounds() {
        for (int i = 0; i < 5; i++) {
            gameEngine.playRound(Move.SCISSORS);
        }
        var score = gameEngine.getScore();
        int total = score.get("Tester") + score.get("Computer") + score.get("Ties");
        assertEquals(5, total);
    }
}
