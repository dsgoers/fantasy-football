package org.dsgoers.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import org.dsgoers.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
