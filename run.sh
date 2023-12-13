#!/bin/bash
echo "Stopping already running services..."
servicePath=./PizzaToGo/services
cd "$servicePath" || exit 1

shopt -s nullglob
serviceArray=(*/)
shopt -u nullglob 
echo "Service directories found: ${serviceArray[@]}"

docker compose down  # Stop currently running services if any

docker compose up -d --build  # Bring up services

for serviceDir in "${serviceArray[@]}"; do
    (

        serviceName="${serviceDir%/}"
        cd $serviceName
        # Check if dockerDB exists in the directory and if so, get the logs for the database service
        if ls -d ./* | grep 'dockerDB'; then
            docker compose logs -f "${serviceName}_database" > "./var/logs/${serviceName}_database.log" 2>&1 &
        fi

        docker compose logs -f "$serviceName" > "./var/logs/${serviceName}.log" 2>&1 &
        cd ..
    ) &
done


wait

docker compose up --build  # Bring up services again (if needed)


$SHELL