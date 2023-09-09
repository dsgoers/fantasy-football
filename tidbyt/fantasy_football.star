load("render.star", "render")
load("http.star", "http")

SCOREBOARD_URL = "http://localhost:8080/"


def main():
    #rep = http.get(SCOREBOARD_URL)
    #if rep.status_code != 200:
    #    fail("Scoreboard request failed with status %d", rep.status_code)

    return render.Root(
        render.Column(
            children=[
                render.Marquee(
                    width=64,
                    child=render.Text("Team 1: 89, Team 2: 95"),
                    offset_start=5,
                    offset_end=32,
                ),
                render.Marquee(
                    width=64,
                    child=render.Text("Team 3: 33, Team 4: 76"),
                    offset_start=5,
                    offset_end=32,
                ),
                render.Marquee(
                    width=64,
                    child=render.Text("Team 5: 45, Team 6: 90"),
                    offset_start=5,
                    offset_end=32,
                ),
                render.Marquee(
                    width=64,
                    child=render.Text("Team 7: 67, Team 8: 77"),
                    offset_start=5,
                    offset_end=32,
                ),
                render.Marquee(
                    width=64,
                    child=render.Text("Team 9: 61, Team 10: 100"),
                    offset_start=5,
                    offset_end=32,
                )
            ]
        )
    )
