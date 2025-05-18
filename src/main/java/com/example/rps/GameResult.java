package com.example.rps;

public class GameResult {
    private final String playerName;
    private final Move playerMove;
    private final String computerName;
    private final Move computerMove;
    private final Result result;

    public String getPlayerName() { return playerName; }
    public Move getPlayerMove() { return playerMove; }
    public String getComputerName() { return computerName; }
    public Move getComputerMove() { return computerMove; }
    public Result getResult() { return result; }

    public GameResult(String playerName, Move playerMove,
                      String computerName, Move computerMove, Result result) {
        this.playerName = playerName;
        this.playerMove = playerMove;
        this.computerName = computerName;
        this.computerMove = computerMove;
        this.result = result;
    }

    public void printResult() {
        System.out.println(playerName + " chose " + playerMove);
        System.out.println(computerName + " chose " + computerMove);
        System.out.println("Result: " + result);
    }


}
