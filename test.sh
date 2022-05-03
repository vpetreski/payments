#!/usr/bin/env bash

# Docker clean slate
docker-compose down
docker rmi payments:latest

# Start (as a daemon) DB and Redis containers only for tests and local dev
docker-compose up -d db redis

# Run tests via Maven
mvn clean
mvn test
