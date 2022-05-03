#!/usr/bin/env bash

# Docker clean slate
docker-compose down
docker rmi payments:latest

# Start (as a daemon) DB and Redis container only for tests and local dev
docker-compose up -d db redis

# You can now start PaymentsApplication from your IDE
