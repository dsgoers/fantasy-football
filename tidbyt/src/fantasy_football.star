load("render.star", "render")
load("http.star", "http")

BASE_URL = "http://localhost:8080/fantasy-football/"


def main():
    return render.Root(
        delay=3000,
        child=render.Text("Fantasy Football is working on Tidbyt")
    )
