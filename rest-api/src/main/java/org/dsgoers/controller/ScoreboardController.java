package org.dsgoers.controller;

import org.dsgoers.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ScoreboardController {

    @Autowired
    private Main main;

    @GetMapping(path = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getScoreboard() throws IOException, InterruptedException {
        final String scoreboard = main.main();

        return ResponseEntity.ok(scoreboard);
    }

}