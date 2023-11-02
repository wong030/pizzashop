# PizzaToGo Project Readme

## Project Structure

This project is organized using Java with Maven as the build tool, and it follows the MicroProfile architecture. The project is divided into two main components:

### 1. services

The `services` folder contains microservices responsible for different aspects of the PizzaToGo application:

- **User Microservice**: Handles user-related functionalities.
- **Delivery Microservice**: Manages delivery-related operations.
- **Procurement Microservice**: Deals with procurement and supply chain management.
- **Production Microservice**: Manages pizza production processes.

Each microservice has its own module within the `services` folder.

### 2. ui

The `ui` folder contains the user interface component of the PizzaToGo application. It interacts with the microservices in the `services` folder.