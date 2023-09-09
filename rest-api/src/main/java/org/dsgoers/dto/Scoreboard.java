package org.dsgoers.dto;

import lombok.Data;

import java.util.List;

@Data
public class Scoreboard {

    private List<Matchup> matchups = List.of();

}
