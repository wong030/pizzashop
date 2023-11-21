# PizzaToGo Project Readme

## Project Structure

This project is organized using Java with Maven as the build tool, and it follows the MicroProfile architecture.

Think of this project as a multimodule project.

### 1. Services

The `services` folder contains microservices responsible for different aspects of the PizzaToGo application:

- **Production Microservice**: Manages pizza production processes. => `localhost:9081`
- **User Microservice**: Handles user-related functionalities.  => `localhost:9082`
- **Procurement Microservice**: Deals with procurement => `localhost:9083`
- **Delivery Microservice**: Manages delivery-related operations. => `localhost:9084`
- **Shop Microservice**: => Shop activities `localhost:9085`
--**UI**: => The `ui` folder contains the user interface component of the PizzaToGo application. It is hosted at `localhost:3000`

Each microservice has its own module within the `services` folder.

### Installation

Run this command from the project root to build the project/all modules. 
Ensure you have execute rights. 
```sh
  ./build.sh
```




