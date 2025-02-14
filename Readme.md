# Reactive Java Backend with Spring Boot and MySQL (R2DBC)

## 📌 Introduction
This project is a **reactive backend** built using **Java**, **Spring Boot**, and **MySQL** with **R2DBC** for non-blocking database interactions. It provides full **CRUD (Create, Read, Update, Delete) operations** while following a **well-structured design pattern** to ensure clean, maintainable, and scalable code.


## 🚀 Features
- **Reactive programming** with Project Reactor
- **Spring Boot** for rapid development
- **MySQL with R2DBC** for non-blocking database operations
- **CRUD operations** with REST APIs
- **Well-structured design pattern**
- **DTO (Data Transfer Object) based service layer**
- **Spring Data R2DBC Repository** for database interactions
- **Exception handling & validation**

---

## 🛠 Tech Stack
- **Java 21**
- **Spring Boot**
- **Spring WebFlux** (for reactive programming)
- **Spring Data R2DBC** (for reactive database interactions)
- **MySQL** (Database)
- **R2DBC Connector** (for asynchronous DB access)
- **Lombok** (to reduce boilerplate code)
- **Maven** (for dependency management)

---

## 📂 Project Structure
The project follows a **layered architecture**:

```plaintext
📦 src/main/java/com/example
 ┣ 📂 entity          # Entity model (Database Representation)
 ┣ 📂 dto             # Data Transfer Objects
 ┣ 📂 repository      # Spring Data R2DBC Repository
 ┣ 📂 service
 ┃ ┣ 📄 Service.java       # Interface defining business logic
 ┃ ┗ 📄 ServiceImpl.java   # Implementation of business logic
 ┣ 📂 controller      # Handles API requests
 ┣ 📂 exception       # Global exception handling
 ┣ 📄 Application.java # Main Spring Boot Application

🔧 Installation & Setup
Prerequisites

    Java 21
    Maven
    MySQL
    Postman (optional for testing APIs)

Clone the Repository

git clone https://github.com/MendiOP/Reactive-Programming
cd your-repo-name

Configure Database

Update application.properties (or application.yml) with your MySQL connection details:

spring.r2dbc.url=r2dbc:mysql://localhost:3306/your_database
spring.r2dbc.username=root
spring.r2dbc.password=yourpassword
spring.sql.init.platform=mysql

Build & Run

mvn clean install
mvn spring-boot:run

📌 API Endpoints
HTTP Method	Endpoint	Description
POST	/books	Create a new record
GET	/books/id/{id}	Get a record by ID
PUT	/books/update/{id}	Update a record by ID
DELETE	/books/delete/{id}	Delete a record by ID
GET	/books	Retrieve all records
🛠 Usage
Create a New Record

POST /books
Content-Type: application/json

{
  "name": "Kalbela",
  "author": "Sunil",
  "publisher": "Onno Prokash",
  "description": "An Uponnash"
}

Get a Record by ID

GET /books/id/1
Update a Record

PUT /books/update/1
Content-Type: application/json

{
  "name": "Kalbela",
  "author": "Sunil",
  "publisher": "Onno Prokash",
  "description": "An Uponnash"
}

Delete a Record

DELETE /books/delete/1
🔍 Troubleshooting

    Database Connection Issue?
    Ensure MySQL is running and the credentials in application.properties are correct.

    Maven Build Failing?
    Try running:

mvn clean install -U

Port Already in Use?
Change the default port in application.properties:

    server.port=8081

🤝 Contributors

    AZM Mehedi Hasan - Creator & Developer

📄 License

This project is licensed under the MIT License.


This markdown document is now properly formatted and structured for clear presentation and ease of us