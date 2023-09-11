package org.dsgoers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dsgoers.dto.MatchupTeam;
import org.dsgoers.dto.Scoreboard;
import org.dsgoers.dto.external.Matchup;
import org.dsgoers.dto.external.Schedule;
import org.dsgoers.dto.external.Teams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Main {

    @Value("${FANTASY_FOOTBALL_BASE_URL}")
    private String baseUrl;

    public String main() throws IOException, InterruptedException {
        final String scheduleQueryString = "view=mMatchup&view=mMatchupScore&scoringPeriodId=1";

        final HttpClient httpClient = HttpClient.newHttpClient();
        final HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final HttpRequest scheduleRequest = HttpRequest
                .newBuilder(URI.create(baseUrl + "?" + scheduleQueryString))
                .build();
        final HttpResponse<String> scheduleResponse = httpClient.send(scheduleRequest, bodyHandler);
        final Schedule schedule = objectMapper.readValue(scheduleResponse.body(), Schedule.class);

        final int weekPeriodId = 1;
        final List<Matchup> weekMatchups = new ArrayList<>();

        schedule.getSchedule().forEach(matchup -> {
            if (matchup.getMatchupPeriodId() == weekPeriodId) {
                weekMatchups.add(matchup);
            }
        });

        final HttpRequest teamsRequest = HttpRequest
                .newBuilder(URI.create(baseUrl))
                .build();
        final HttpResponse<String> teamsResponse = httpClient.send(teamsRequest, bodyHandler);
        final Teams teams = objectMapper.readValue(teamsResponse.body(), Teams.class);

        final Scoreboard scoreboard = mapToScoreboard(weekMatchups, teams);

        final String scoreboardString = objectMapper.writeValueAsString(scoreboard);

        return scoreboardString;
    }

    private static Scoreboard mapToScoreboard(final List<Matchup> weekMatchups, final Teams teams) {
        final List<org.dsgoers.dto.Matchup> matchups = weekMatchups
                .stream()
                .map(externalMatchup -> {
                    final org.dsgoers.dto.Matchup matchup = new org.dsgoers.dto.Matchup();
                    matchup.setAway(externalToMatchupTeam(externalMatchup.getAway()));
                    matchup.setHome(externalToMatchupTeam(externalMatchup.getHome()));

                    final String homeName = teams
                            .getTeams()
                            .stream()
                            .filter(team -> team.getId() == externalMatchup.getHome().getTeamId())
                            .findFirst()
                            .get()
                            .getNickname();
                    final String awayName = teams
                            .getTeams()
                            .stream()
                            .filter(team -> team.getId() == externalMatchup.getAway().getTeamId())
                            .findFirst()
                            .get()
                            .getNickname();

                    matchup.getAway().setName(awayName);
                    matchup.getHome().setName(homeName);

                    return matchup;
                }).collect(Collectors.toList());

        final Scoreboard scoreboard = new Scoreboard();
        scoreboard.setMatchups(matchups);

        return scoreboard;
    }

    private static MatchupTeam externalToMatchupTeam(final org.dsgoers.dto.external.MatchupTeam externalMatchupTeam) {
        final MatchupTeam matchupTeam = new MatchupTeam();
        matchupTeam.setName(Integer.toString(externalMatchupTeam.getTeamId()));
        matchupTeam.setScore(externalMatchupTeam.getTotalPointsLive());

        return matchupTeam;
    }

}
