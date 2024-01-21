#!/bin/bash

/usr/local/bin/pixlet render /fantasy_football/tidbyt/src/fantasy_football.star -o /fantasy_football.webp
/usr/local/bin/pixlet push --installation-id $TIDBYT_FANTASY_FOOTBALL_APP_NAME $TIDBYT_DEVICE_ID /fantasy_football.webp