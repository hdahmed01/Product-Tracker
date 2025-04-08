#Product and Provider Management System
Overview
This project is a Product and Provider Management System built with Spring Boot and the MVC design pattern. The system allows users to manage products and their corresponding providers. It demonstrates the use of core Spring technologies, including Spring MVC, Thymeleaf, Spring Validation, and Spring Security.

Features
Product Management: Add, edit, and delete product details.

Provider Management: Add and manage providers associated with products.

User Authentication and Authorization: Users are authenticated and authorized using three different security mechanisms.

InMemoryUserDetailsManager: In-memory authentication for simple use cases.

JdbcUserDetailsManager: JDBC-based user management with a database schema.

UserDetailsService: Custom user details service with roles and permissions.

Form Validation: Ensure that product and provider information is validated using Spring Validation.

Technologies Used
Spring Boot: Backend framework for creating RESTful services and web applications.

Spring MVC: Architecture pattern used for separating the concerns of the application (Model, View, Controller).

Thymeleaf: Template engine used for rendering dynamic HTML views.

Spring Validation: For validating user inputs and ensuring data integrity.

Spring Security: For securing the application and managing user authentication and authorization.

H2 Database (or any database you choose): For storing product and provider data.

Setup
Prerequisites
Before running this project, make sure you have the following installed:

Java 8+ (or later)

Maven (for building the project)

IDE (like IntelliJ IDEA, Eclipse, or Spring Tool Suite)

Database: H2 (default) or any relational database (MySQL, PostgreSQL, etc.)

Clone the Repository
bash
Copy
Edit
git clone https://github.com/yourusername/product-provider-management.git
cd product-provider-management
Build and Run the Project
To build the project and run it locally, use Maven:

bash
Copy
Edit
mvn spring-boot:run
The application will be available at http://localhost:9090.

Accessing the Application
Login Page: You will be redirected to a login page for authentication.

Default credentials for testing (for the InMemory security option):

Username: user1

Password: 123

Customizing Security
You can choose between different security configurations:

InMemoryUserDetailsManager: Simple, in-memory authentication with hardcoded users.

JdbcUserDetailsManager: Uses database authentication methods and a database schema for user management.

UserDetailsService: Custom authentication using personalized user and role entities, with services and repositories.
