# Project Name: UseCase Spring Boot Application (Company and contact)

## Description

This project is a Spring Boot application designed for managing contacts and companies within a corporate environment. It provides APIs to create, update, delete, and retrieve contacts and companies, associate contacts with companies, and search companies by their VAT number.

## Features

- **Contact Management**: Create, update, delete, and retrieve contacts.
- **Company Management**: Create, update, delete, and retrieve companies.
- **Search**: Look up a company using its VAT number.
- **Association**: Add contacts to companies, supporting many-to-many relationships.

## Technologies Used

- Spring Boot 3.2.5
- Spring Data JPA
- H2 Database
- Maven
- Swagger UI for API documentation

Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites

What you need to install the software:

- Java JDK 11..17
- Maven
- Your favorite IDE (Eclipse, IntelliJ, etc.)

Installing

A step-by-step series of examples that tell you how to get a development environment running:

1. **Clone the repository**
   git clone https://github.com/khalux/usecasespringboot.git
2. **Navigate to the project directory**
cd your-repository
3. **Install dependencies**
	mvn install
4. **Run the application**
   mvn spring-boot:run
   The application will start running at http://localhost:8081.

**API Documentation**
After running the application, you can access the Swagger UI at http://localhost:8081/swagger-ui.html to interact with the API.
