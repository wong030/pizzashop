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

        # Get logs for the current service
        docker compose logs -f "$serviceName" > "./var/logs/${serviceName}.log" 2>&1 &
        cd ..
    ) &
done

# Wait for all background processes to complete before continuing
wait

docker compose up --build  # Bring up services again (if needed)

# serviceArr = ($services)
# echo $serviceArr
# docker-compose up -d $services --build

# # save logs to a file in the background

# logConsumer =  
# docker-compose logs -f ui > ./PizzaToGo/services/ui/var/logs/ui.log 2>&1 &
# docker-compose logs -f delivery > ./PizzaToGo/services/delivery/var/logs/delivery.log 2>&1 &
# docker-compose logs -f procurement > ./PizzaToGo/services/procurement/var/logs/procurement.log 2>&1 &
# docker-compose logs -f production > ./PizzaToGo/services/production/var/logs/production.log 2>&1 &
# docker-compose logs -f shop > ./PizzaToGo/services/shop/var/logs/shop.log 2>&1 &
# docker-compose logs -f user > ./PizzaToGo/services/user/var/logs/user.log 2>&1 &


# docker-compose up $services --build


$SHELL