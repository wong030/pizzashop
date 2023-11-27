#!/bin/sh

echo "Build docker images..."
imagePath=./PizzaToGo/services


cd $imagePath
# servicePath = 
shopt -s nullglob
serviceArray=(*/)
shopt -u nullglob # Turn off nullglob to make sure it doesn't interfere with anything later
echo "${serviceArray[@]}"


for serviceDir in "${serviceArray[@]}"; do
(
    serviceName="${serviceDir%/}"
    echo "Building Docker image for: $serviceName"
    # Navigate into the service directory
    cd "$serviceName" || continue

    # Build the Docker image (replace <your_image_name> and <your_tag> with your image name and tag)
    docker build -t "$serviceName":latest .
    # Navigate back to the parent directory
    cd ..
) &
done


$SHELL