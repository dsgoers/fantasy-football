#!/bin/zsh

pixlet render tidbyt/src/fantasy_football.star -o tidbyt/target/fantasy_football.webp
pixlet push --installation-id $TIDBYT_FANTASY_FOOTBALL_APP_NAME $TIDBYT_DEVICE_ID tidbyt/target/fantasy_football.webp