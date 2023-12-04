#!/bin/sh
echo "Building Microservices with project builder..."

$projectDir = "./PizzaToGo"
./mvnw clean package -f $projectDir -T 1C

$SHELL 