package org.dsgoers.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class Schedule {

  private List<Matchup> schedule = List.of();
}
