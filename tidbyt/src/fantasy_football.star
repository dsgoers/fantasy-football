load("render.star", "render")
load("http.star", "http")

SCOREBOARD_URL = "http://localhost:8080/"


def main():
    return render.Root(
        delay=3000,
        child=render.Text("Fantasy Football is working on Tidbyt")
    )
