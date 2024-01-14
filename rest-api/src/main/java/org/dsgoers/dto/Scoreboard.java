package org.dsgoers.dto;

import java.util.List;
import lombok.Data;

@Data
public class Scoreboard {

  private List<Matchup> matchups = List.of();
}
