package com.example.rps;

public class Player {
    private final String name;
    private Move move;

    public Player(String name) {
        this.name = name;
    }

    public void makeMove(Move move) {
        this.move = move;
    }

    public void makeRandomMove() {
        this.move = Move.getRandomMove();
    }

    public Move getMove() {
        return move;
    }

    public String getName() {
        return name;
    }
}
