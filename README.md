# Product Catalog REST API

A Spring Boot application that provides REST APIs for managing product categories and products with pagination support.

## Features

- Category CRUD operations
- Product CRUD operations
- One-to-many relationship between Category and Products
- Server-side pagination
- MySQL database integration
- RESTful API design

## Technologies

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## Prerequisites

- JDK 17+
- Maven 3.6+
- MySQL

## Getting Started

### Database Configuration

Configure your MySQL database connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_catalog?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
