echo "Running in dev mode"

echo "Starting databases and services"


cd PizzaToGo && ./mvnw liberty:dev -f ./services/shop & ./mvnw liberty:dev -f ./services/user & docker compose up user_database shop_database delivery procurement production 

$SHELL