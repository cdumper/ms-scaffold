#!/bin/sh

./gradlew clean build
docker-compose up --build -d