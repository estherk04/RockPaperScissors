package com.example.rps;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GameController {

    private GameEngine gameEngine = new GameEngine("Player"); // default name

    @PostMapping("/start")
    public void startGame(@RequestBody Map<String, String> body) {
        String name = body.getOrDefault("name", "Player");
        gameEngine = new GameEngine(name);
    }

    @PostMapping("/play")
    public GameResult playRound(@RequestBody Map<String, String> body) {
        Move playerMove = Move.valueOf(body.get("move"));
        return gameEngine.playRound(playerMove);
    }

    // In GameController.java
    @GetMapping("/score")
    public Map<String, Integer> getScore() {
        return gameEngine.getScore();
    }
}
