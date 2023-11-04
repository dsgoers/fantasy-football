load("render.star", "render")
load("http.star", "http")

SCOREBOARD_URL = "http://localhost:8080/"


def main():
    rep = http.get(SCOREBOARD_URL)
    if rep.status_code != 200:
        fail("Scoreboard request failed with status %d", rep.status_code)

    matchups = rep.json()["matchups"]

    scoreboardTidbytElements = []

    for matchup in matchups:
        scoreboardTidbytElements.append(
            render.Column(
                children=[
                    render.Marquee(
                        width=64,
                        child=render.Text(matchup["home"]["name"] + ": " + str(matchup["home"]["score"])),
                        offset_start=5,
                        offset_end=32,
                    ),
                    render.Marquee(
                        width=64,
                        child=render.Text(matchup["away"]["name"] + ": " + str(matchup["away"]["score"])),
                        offset_start=5,
                        offset_end=32,
                    )
                ]
            )
        )

    return render.Root(
        delay=3000,
        child=render.Sequence(
            children=scoreboardTidbytElements,
        )

    )
