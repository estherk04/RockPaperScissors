package com.example.rps;

import java.util.Map;

public class GameEngine {

    private final Player player;
    private final Player computer;

    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    public GameEngine(String playerName) {
        this.player = new Player(playerName);
        this.computer = new Player("Computer");
    }

    public GameResult playRound(Move playerMove) {
        player.makeMove(playerMove);
        computer.makeRandomMove();

        Result result = determineResult(player.getMove(), computer.getMove());

        // Update score
        switch (result) {
            case WIN -> playerWins++;
            case LOSE -> computerWins++;
            case TIE -> ties++;
        }

        return new GameResult(player.getName(), player.getMove(),
                computer.getName(), computer.getMove(), result);
    }

    private Result determineResult(Move playerMove, Move computerMove) {
        if (playerMove == computerMove) return Result.TIE;

        return switch (playerMove) {
            case ROCK -> (computerMove == Move.SCISSORS) ? Result.WIN : Result.LOSE;
            case PAPER -> (computerMove == Move.ROCK) ? Result.WIN : Result.LOSE;
            case SCISSORS -> (computerMove == Move.PAPER) ? Result.WIN : Result.LOSE;
        };
    }

    public void printFinalScore() {
        System.out.println("\n🏁 Final Score:");
        System.out.println(player.getName() + ": " + playerWins + " wins");
        System.out.println(computer.getName() + ": " + computerWins + " wins");
        System.out.println("Ties: " + ties);
    }
    // In GameEngine.java
    public Map<String, Integer> getScore() {
        return Map.of(
                player.getName(), playerWins,
                computer.getName(), computerWins,
                "Ties", ties
        );
    }

}
