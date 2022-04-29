#!/usr/bin/env bash

# Docker clean slate
docker-compose down
docker rmi payments:latest

# Start (as a daemon) DB container only for tests and local dev
docker-compose up -d db redis

# Run tests via Maven
mvn clean
mvn test

# Run the client
mvn exec:java -Dexec.mainClass="com.payments.backend.PaymentsClient"