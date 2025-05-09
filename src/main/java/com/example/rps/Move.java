package com.example.rps;

import java.util.concurrent.ThreadLocalRandom;

public enum Move {
    ROCK, PAPER, SCISSORS;

    public static Move getRandomMove() {
        Move[] values = Move.values();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
