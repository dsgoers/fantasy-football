FROM ubuntu:latest
LABEL authors="dgoers"

COPY target/fantasy-football-1.0-SNAPSHOT.jar fantasy_football/
COPY tidbyt.sh fantasy_football/
COPY tidbyt/src/fantasy_football.star fantasy_football/tidbyt/src/

USER root
RUN apt-get -y update; apt-get -y install pixlet; apt-get install -y openjdk-21-jre;