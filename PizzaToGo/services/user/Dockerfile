FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at the specified location
COPY target/shop.jar /app/app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]