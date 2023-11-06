procurement# PizzaToGo Project Readme

## Project Structure

This project is organized using Java with Maven as the build tool, and it follows the MicroProfile architecture. The project is divided into two main components:

### 1. Services

The `services` folder contains microservices responsible for different aspects of the PizzaToGo application:

- **Production Microservice**: Manages pizza production processes. => `localhost:9081`
- **User Microservice**: Handles user-related functionalities.  => `localhost:9082`
- **Procurement Microservice**: Deals with procurement => `localhost:9083`
- **Delivery Microservice**: Manages delivery-related operations. => `localhost:9084`
- **Shop Microservice**: => Shop activities `localhost:9085`


Each microservice has its own module within the `services` folder.

### 2. UI

The `ui` folder contains the user interface component of the PizzaToGo application. It interacts with the microservices in the `services` folder.