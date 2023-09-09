load("render.star", "render")
load("http.star", "http")

SCOREBOARD_URL = "http://localhost:8080/"


def main():
    rep = http.get(SCOREBOARD_URL)
    if rep.status_code != 200:
        fail("Scoreboard request failed with status %d", rep.status_code)

    return render.Root(
        child=render.Text("Team 1: 73, Team 2: 71")
    )
