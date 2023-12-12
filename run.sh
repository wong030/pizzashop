#!/bin/bash
echo "Down already running servives..."

docker-compose up -d ui delivery procurement production shop user

# save logs to a file in the background
docker-compose logs -f ui > ./PizzaToGo/services/ui/var/logs/ui.log 2>&1 &
docker-compose logs -f delivery > ./PizzaToGo/services/delivery/var/logs/delivery.log 2>&1 &
docker-compose logs -f procurement > ./PizzaToGo/services/procurement/var/logs/procurement.log 2>&1 &
docker-compose logs -f production > ./PizzaToGo/services/production/var/logs/production.log 2>&1 &
docker-compose logs -f shop > ./PizzaToGo/services/shop/var/logs/shop.log 2>&1 &
docker-compose logs -f user > ./PizzaToGo/services/user/var/logs/user.log 


# reattach to the container to be able to do control-c
# it will not rerun the container, just reattach
docker-compose up ui delivery production procurement user shop

# imagePath=./PizzaToGo/services
# logPath=var/logs/
# cd $imagePath
# echo "Pwd: $(pwd)"
#  shopt -s nullglob
#  serviceArray=(*/)
#  shopt -u nullglob 



# composeServicePayload=$(echo "${serviceArray[*]}" | sed 's/\// /g')
# echo "Payload: $composeServicePayload"

# echo (read -r -a myArray <<< "$composeServicePayload")

# docker compose down   
# echo "Start project with docker compose..."
# echo "Rebuild containers..."


# docker compose up $composeServicePayload --build

 $SHELL