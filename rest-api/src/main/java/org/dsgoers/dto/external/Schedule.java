package org.dsgoers.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class Schedule {

    private List<Matchup> schedule = List.of();

}
