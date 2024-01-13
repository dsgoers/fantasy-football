package org.dsgoers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dsgoers.dto.MatchupTeam;
import org.dsgoers.dto.Scoreboard;
import org.dsgoers.dto.external.Matchup;
import org.dsgoers.dto.external.Schedule;
import org.dsgoers.dto.external.Team;
import org.dsgoers.dto.external.Teams;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Main {

    private final String baseUrl;

    public Main(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Main(null).main();
    }

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

        final long weekPeriodId = calculateWeek();
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

    private long calculateWeek() {
        final ZonedDateTime start = ZonedDateTime.of(LocalDate.of(2023, 9, 5).atStartOfDay(), ZoneId.systemDefault());
        final ZonedDateTime end = ZonedDateTime.now();

        return ChronoUnit.WEEKS.between(start, end) + 1;
    }

    private static Scoreboard mapToScoreboard(final List<Matchup> weekMatchups, final Teams teams) {
        final List<org.dsgoers.dto.Matchup> matchups = weekMatchups
                .stream()
                .map(externalMatchup -> {
                    final org.dsgoers.dto.Matchup matchup = new org.dsgoers.dto.Matchup();
                    matchup.setAway(externalToMatchupTeam(externalMatchup.getAway()));
                    matchup.setHome(externalToMatchupTeam(externalMatchup.getHome()));

                    final Team homeTeam = teams
                            .getTeams()
                            .stream()
                            .filter(team -> team.getId() == externalMatchup.getHome().getTeamId())
                            .findFirst()
                            .get();
                    final Team awayTeam = teams
                            .getTeams()
                            .stream()
                            .filter(team -> team.getId() == externalMatchup.getAway().getTeamId())
                            .findFirst()
                            .get();

                    matchup.getAway().setName(Optional.ofNullable(awayTeam.getNickname()).orElse(awayTeam.getUserFirstName()));
                    matchup.getHome().setName(Optional.ofNullable(homeTeam.getNickname()).orElse(homeTeam.getUserFirstName()));

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
