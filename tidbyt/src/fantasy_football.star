load("render.star", "render")
load("http.star", "http")
load("time.star", "time")

BASE_URL = "http://localhost:8080/fantasy-football/"
SCOREBOARD_URL = "http://localhost:8080/fantasy-football/" + "scoreboard"


def main():
    time.now()

    return render.Root(
        delay=3000,
        child=render.Text(str(time.now().hour) + ":" + str(time.now().minute))
    )
