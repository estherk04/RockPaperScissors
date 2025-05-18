package com.example.rps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testStartGame() throws Exception {
        Map<String, String> body = Map.of("name", "Malka");

        mockMvc.perform(post("/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk());
    }

    @Test
    void testPlayRound() throws Exception {
        mockMvc.perform(post("/start")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Malka\"}"));

        Map<String, String> body = Map.of("move", "ROCK");

        mockMvc.perform(post("/play")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerMove").value("ROCK"));
    }
}
