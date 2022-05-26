#!/usr/bin/env bash

kubectl create -f k8s/redis-deploy.yml
kubectl create -f k8s/redis-service.yml
kubectl create -f k8s/postgres-deploy.yml
kubectl create -f k8s/postgres-service.yml
kubectl create -f k8s/payments-deploy.yml
kubectl create -f k8s/payments-service.yml
echo "Sleeping 5s..."
sleep 5
echo "Open http://localhost:30009/doc"
kubectl port-forward service/payments-service 30009:8080
