#!/usr/bin/env bash

# Docker clean slate
docker-compose down
docker rmi payments:latest

# Maven build
mvn clean
mvn package -DskipTests

# Start containers
docker-compose up

# TODO - call the client (curl example, client example, but outside of this script...)