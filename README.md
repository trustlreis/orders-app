# Orders Management System

## Overview
This is an Orders Management System built with Spring Boot. The application provides RESTful APIs for managing customers, orders, and order details. It uses PostgreSQL as the database, with Flyway for database migrations.

## Features
- REST API for managing customers, orders, and order details
- Database migrations with Flyway
- Profiles for development and production environments
- PostgreSQL database
- Report on orders grouped by customers, counting orders and products by year-month

## Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher
- Docker and Docker Compose

## Project Structure
```
.
├── Dockerfile
├── docker-compose.yml
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── orders
│   │               ├── OrdersApplication.java
│   │               ├── config
│   │               │   └── DatabaseConfig.java
│   │               ├── controller
│   │               │   ├── CustomerController.java
│   │               │   └── OrderController.java
│   │               ├── model
│   │               │   ├── Customer.java
│   │               │   ├── Order.java
│   │               │   └── OrderDetail.java
│   │               ├── repository
│   │               │   ├── CustomerRepository.java
│   │               │   ├── OrderDetailRepository.java
│   │               │   └── OrderRepository.java
│   │               └── service
│   │                   ├── CustomerService.java
│   │                   └── OrderService.java
│   └── resources
│       ├── application-local.yaml
│       ├── application.yaml
│       ├── db
│       │   └── migration
│       │       └── V1__Initial_Setup.sql
│       ├── static
│       └── templates
└── test
        └── java
            └── com
                └── example
                    └── orders
                        └── OrdersApplicationTests.java
```

## Building the Project

### Using Maven
To build the project with Maven, run the following command:
```sh
./mvnw clean package
```

This will compile the project, run the tests, and package the application into a JAR file.

## Running the Application

### Using Docker Compose
#### Development Environment
To run the application in the development environment, use the following command:
```sh
docker-compose up --build
```

This command will:
- Build the Docker image for the application
- Start a PostgreSQL container
- Start the Spring Boot application container with the `dev` profile

The application will be accessible at `http://localhost:8080`.

#### Production Environment
To run the application in the production environment, use the following command:
```sh
SPRING_PROFILES_ACTIVE=prod docker-compose up --build
```

This command will:
- Build the Docker image for the application
- Start a PostgreSQL container
- Start the Spring Boot application container with the `prod` profile

### Local Development
For local development, you might want to run the application on your machine while still using PostgreSQL in a Docker container. Follow these steps:

1. **Start PostgreSQL with Docker Compose:**
   ```sh
   docker-compose up db
   ```

2. **Run the Application Locally:**
   Ensure that the `local` profile is active in your IDE or using environment variables:
   ```sh
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
   ```

   Alternatively, you can run the JAR file directly:
   ```sh
   java -jar target/orders-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
   ```

## Application Configuration

### `application.yaml`
Defines common configuration settings and specifies the active profile.
```yaml
spring:
  profiles:
    active: dev

  datasource:
    driver-class-name: org.postgresql.Driver
    username: postgres
    password: password
    url: jdbc:postgresql://db:5432/db

  flyway:
    enabled: true
    locations: classpath:db/migration
```

### `application-local.yaml`
Local development-specific configuration.
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/db
    schema: public

  flyway:
    schemas: public
```

## API Endpoints
- `GET /api/customers` - Retrieve all customers
- `POST /api/customers` - Create a new customer
- `GET /api/orders` - Retrieve all orders
- `POST /api/orders` - Create a new order
- `GET /api/orders/{orderId}/details` - Retrieve order details for a specific order
- `POST /api/orders/{orderId}/details` - Add order details to a specific order
- `GET /api/orders/report` - Retrieve a report of orders grouped by customers, counting orders and products by year-month

## Database Migrations
Flyway is used for database migrations. Migration scripts are located in `src/main/resources/db/migration`.

## Contributing
Feel free to open issues or submit pull requests for any improvements or bug fixes.

## License
This project is licensed under the MIT License.
