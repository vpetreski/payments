#!/usr/bin/env bash

kubectl delete deployment postgres-deploy
kubectl delete deployment redis-deploy
kubectl delete deployment payments-deploy
kubectl delete service db
kubectl delete service redis
kubectl delete service payments-service
