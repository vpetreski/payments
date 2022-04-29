#!/usr/bin/env bash

# Docker clean slate
docker-compose down
docker rmi payments:latest

# Maven build
mvn clean
mvn package -DskipTests

# Start containers
docker-compose up