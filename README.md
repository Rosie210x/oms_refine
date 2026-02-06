# 📖 Order Management System (OMS)

## Description
The **Order Management System (OMS)** is a Spring Boot web application designed to manage customers and their orders.  
It demonstrates:
- DTO ↔ Entity mapping
- Input validation (manual + utility class)
- Controller → Service → Repository workflow
- JSP form integration with dropdowns (countries) and text inputs (customer details)

This project is ideal for learning **backend workflow orchestration**, **validation strategies**, and **basic CRUD operations** with Spring Boot and JPA.

---

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Technologies](#technologies)
- [Contributing](#contributing)

---

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/oms_refine.git
      ```
2. Navigate into the project folder:
   ```bash
   cd order-management-system
   ```
3. Configure your database (recommended using SQL Server). Update `application.properties`:
   ```properties
   spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=your_db_name;encrypt=true;trustServerCertificate=true
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
4. Build and run the project:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## Usage
- Open the app in your browser: `http://localhost:8080/order-form`
- Fill in customer details and select a country from the dropdown.
- Submit the form to create a new order.
- Navigate to `/view-orders` to see all orders.
- Click on an order to view its details.

---

## Features
- ✅ Customer creation with validation:
  - First name, last name, email, address line 1, country and city required
  - Email format validation via `EmailValidation` utility
  - Duplicate email check
- ✅ Country selection via dropdown (preloaded from DB)
- ✅ City creation if not found in DB
- ✅ Order creation (1:1 relationship with Customer)
- ✅ JSP views for forms and order details

---

## Technologies
- **Java 25**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **SQL Server**
- **JSP / JSTL**
- **Lombok**

---

## Contributing
Contributions are welcome!  
1. Fork the repo  
2. Create a new branch (`feature/your-feature`)  
3. Commit changes  
4. Push to your branch  
5. Open a Pull Request  

---


