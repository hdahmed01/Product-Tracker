# Product and Provider Management System

## Overview
This project is a **Product and Provider Management System** built with **Spring Boot** and the **MVC design pattern**. The system allows users to manage products and their corresponding providers. It demonstrates the use of core Spring technologies, including **Spring MVC**, **Thymeleaf**, **Spring Validation**, and **Spring Security**.

### Features
- **Product Management**: Add, edit, and delete product details.
- **Provider Management**: Add and manage providers associated with products.
- **User Authentication and Authorization**: Users are authenticated and authorized using three different security mechanisms.
  - **InMemoryUserDetailsManager**: In-memory authentication for simple use cases.
  - **JdbcUserDetailsManager**: JDBC-based user management with a database schema.
  - **UserDetailsService**: Custom user details service with roles and permissions.
- **Form Validation**: Ensure that product and provider information is validated using **Spring Validation**.

## Technologies Used
- **Spring Boot**: Backend framework for creating RESTful services and web applications.
- **Spring MVC**: Architecture pattern used for separating the concerns of the application (Model, View, Controller).
- **Thymeleaf**: Template engine used for rendering dynamic HTML views.
- **Spring Validation**: For validating user inputs and ensuring data integrity.
- **Spring Security**: For securing the application and managing user authentication and authorization.
- **H2 Database** (or any database you choose): For storing product and provider data.

## Setup

### Prerequisites
Before running this project, make sure you have the following installed:
- **Java 8+** (or later)
- **Maven** (for building the project)
- **IDE** (like IntelliJ IDEA, Eclipse, or Spring Tool Suite)
- **Database**: H2 (default) or any relational database (MySQL, PostgreSQL, etc.)

### Clone the Repository
```bash
git clone https://github.com/yourusername/product-provider-management.git](https://github.com/hdahmed01/Product-Tracker.git
cd product-provider-management
