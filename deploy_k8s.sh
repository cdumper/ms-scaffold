#!/usr/bin/env bash

./gradlew clean buildDocker -x test
eval $(minikube docker-env)
for deployment in $(find . -type d -name "deployment")
do
    kubectl apply -f $deployment
done
