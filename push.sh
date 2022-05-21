#!/usr/bin/env bash

# Maven build
mvn clean
mvn package -DskipTests

# Build & Push to Docker Hub
docker build -t vanjapetreski/spring-boot-payments .
docker push vanjapetreski/spring-boot-payments