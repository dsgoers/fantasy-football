#!/bin/zsh

pixlet render fantasy_football.star
pixlet push --installation-id $TIDBYT_FANTASY_FOOTBALL_APP_NAME $TIDBYT_DEVICE_ID fantasy_football.webp
