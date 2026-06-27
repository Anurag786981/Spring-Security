# Spring Security Authentication & Authorization Demo

This project demonstrates how to secure a Spring Boot application using **Spring Security** with multiple authentication mechanisms:

- 🔑 **JWT Authentication** – Stateless authentication for REST APIs with token generation, validation, and refresh.
- 🔒 **Basic Authentication** – Simple username/password authentication for quick setups and testing.
- 👥 **Role-Based Authorization** – Restrict endpoints to specific user roles and authorities.

---

## 🚀 Features
- Centralized `SecurityConfig` for authentication providers and filters.
- Custom `UserDetailsService` for loading users from a database.
- Exception handling for unauthorized and forbidden requests.
- Example REST endpoints protected by different authentication methods.
- Token-based login and secured API access.

---

## 📂 Project Structure
src/main/java/com/example/security/
│── config/        # Security configuration classes
│── controller/    # REST controllers with secured endpoints
│── dto/           # Data Transfer Objects
│── entity/        # JPA entities
│── repository/    # Spring Data repositories
│── service/       # Business logic and UserDetailsService
