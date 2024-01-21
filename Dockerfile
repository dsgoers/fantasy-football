FROM ubuntu:latest
LABEL authors="dgoers"

COPY target/fantasy-football-1.0-SNAPSHOT.jar fantasy_football/
COPY tidbyt.sh fantasy_football/
COPY tidbyt/src/fantasy_football.star fantasy_football/tidbyt/src/

CMD echo "********** STARTING FANTASY FOOTBALL CONTAINER **********" && service cron start && java -jar fantasy_football/fantasy-football-1.0-SNAPSHOT.jar

USER root
RUN apt-get -y update; apt-get -y install cron; apt-get -y install curl; apt-get install -y openjdk-21-jre; curl -LO https://github.com/tidbyt/pixlet/releases/download/v0.29.1/pixlet_0.29.1_linux_amd64.tar.gz; tar -xvf pixlet_0.29.1_linux_amd64.tar.gz; chmod +x ./pixlet; mv pixlet /usr/local/bin/pixlet; echo "* * * * * /fantasy_football/tidbyt.sh" | crontab -; service cron start;

HEALTHCHECK CMD curl -f http://localhost:8080/fantasy-football/actuator/health
