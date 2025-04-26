# Ecommerce Spring Boot Application

A backend ecommerce API built with Java and Spring Boot. This application provides endpoints for authentication, managing items, and processing orders.

## Features

- User authentication with Spring Security
- JWT-based login system
- CRUD operations for items
- Order creation and management
- Modular project structure with DTOs, services, and controllers

## Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- Maven

## Project Structure

```
src/
├── main/
│   └── java/
│       └── dev/
│           └── elijuh/
│               └── ecommerce/
│                   ├── configuration/     # App & security config
│                   ├── controller/        # REST controllers for auth, items, orders
│                   ├── dto/               # Data Transfer Objects
│                   ├── model/             # Entity models
│                   ├── repository/        # Spring Data repositories
│                   ├── service/           # Business logic services
│                   └── EcommerceApplication.java  # Main class
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- (Optional) MySQL or any preferred RDBMS

### Running the Application

```bash
# Clone the repo
git clone https://github.com/elijuh/ecommerce-backend-spring.git
cd ecommerce-backend-spring

# Run with Maven
./mvnw spring-boot:run
```

### Accessing the App

- API base: `http://localhost:8080/api`
- H2 Console (if enabled): `http://localhost:8080/h2-console`

## Auth Endpoints

- `POST /api/auth` – Login with credentials
- `POST /api/auth/register` – Register a new user

## Item Endpoints

- `GET /api/items` – List all items
- `GET /api/items/{id}` – Get an item by ID
- `GET /api/items/sku/{sku}` – Get an item by SKU
- `POST /api/items/create` – Create a new item
- `DELETE /api/items/{id}` – Delete an item

## Order Endpoints

- `GET /api/orders` – List all orders for the current user
- `GET /api/orders/{userId}` – List all orders for a user
- `GET /api/orders/active` – List all active orders for the current user
- `GET /api/orders/active/{userId}` – List all active orders for a user
- `POST /api/orders/create` – Create a new order for the current user
