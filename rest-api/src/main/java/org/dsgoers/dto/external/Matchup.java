package org.dsgoers.dto.external;

import lombok.Data;

@Data
public class Matchup {

  private int matchupPeriodId;
  private MatchupTeam home;
  private MatchupTeam away;
}
