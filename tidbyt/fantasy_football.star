load("render.star", "render")
load("http.star", "http")

SCOREBOARD_URL = "http://localhost:8080/"


def main():
    #rep = http.get(SCOREBOARD_URL)
    #if rep.status_code != 200:
    #    fail("Scoreboard request failed with status %d", rep.status_code)

    return render.Root(
        delay = 3000,
        child=render.Sequence(
            children=[
                render.Column(
                    children=[
                        render.Marquee(
                            width=64,
                            child=render.Text("Team 1: 89"),
                            offset_start=5,
                            offset_end=32,
                        ),
                        render.Marquee(
                            width=64,
                            child=render.Text("Team 2: 76"),
                            offset_start=5,
                            offset_end=32,
                        )
                    ]
                ),
                render.Column(
                    children=[
                        render.Marquee(
                            width=64,
                            child=render.Text("Team 3: 22"),
                            offset_start=5,
                            offset_end=32,
                        ),
                        render.Marquee(
                            width=64,
                            child=render.Text("Team 4: 68"),
                            offset_start=5,
                            offset_end=32,
                        )
                    ]
                )
            ],
        )

    )
