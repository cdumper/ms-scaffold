#!/bin/sh

./gradlew clean buildDocker -x test
VERSION=1.0-SNAPSHOT docker-compose -f docker-compose.yml up -d
