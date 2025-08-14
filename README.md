<div align="center">

# RESTful API of ForoHub with Spring Boot

This project is a RESTful API built with **Spring Boot** to manage an online discussion forum.  
It allows creating, listing, updating, and deleting (controlled by authentication) discussion topics (*topics*),  
associating them with users and courses, and implements **JWT** authentication to protect API access.

![Java](https://img.shields.io/badge/Java-17+-blue?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.x-blue?logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Security-orange?logo=jsonwebtokens&logoColor=white)

</div>

---

## 🚀 Main Features

- **Full CRUD** for *topics* with:
  - Validations to prevent duplicate titles or messages.
  - Association with **User** and **Course** entities via their IDs.
  - Conversion between entities and DTOs.
  - Pagination with `Pageable` for efficient queries.
- **Authentication and authorization** with **JWT**:
  - Token generation at `/login` from valid credentials.
  - Security filter to validate the token on each request.
  - Permission configuration to allow only authenticated users to access protected endpoints.

---

## 🏗️ Architecture

- **Layered structure**:
  - **Controller** → handles HTTP requests.
  - **Service** → business logic.
  - **Repository** → data access with Spring Data JPA.
- **Best practices**:
  - Dependency injection with `@Autowired`.
  - `@Transactional` on data-modifying methods to ensure consistency.
  - Validations with Jakarta Bean Validation (`@Valid`).
  - Separation of concerns (DTOs, security, entities, etc.).

---

## 📦 Technologies Used

- **Java 17+**
- **Spring Boot** (Web, Data JPA, Security, Validation)
- **Auth0 Java JWT** for token generation/validation
- **MySQL** with **Flyway** for migrations
- **Hibernate** (ORM)
- **Lombok**
- **Maven**

---

## 📌 Main Endpoints

| Method | Route           | Description                       | Authentication |
|--------|----------------|-----------------------------------|---------------|
| POST   | `/login`       | Authentication and token creation | ❌ No         |
| POST   | `/topics`      | Create a new topic                | ✅ Yes        |
| GET    | `/topics`      | List paginated topics             | ✅ Yes        |
| PUT    | `/topics/{id}` | Update an existing topic          | ✅ Yes        |
| DELETE | `/topics/{id}` | Delete topic (hard delete)        | ✅ Yes        |

---

## 🔑 JWT Authentication

1. **Generate token**
   - Send POST to `/login` with a JSON:
     ```json
     {
       "login": "usuarioexample",
       "clave": "password"
     }
     ```
   - Response:
     ```json
     {
       "jwTtoken": "GENERATED_TOKEN"
     }
     ```

2. **Use token in requests**
   - Add to the header:
     ```
     Authorization: Bearer GENERATED_TOKEN
     ```

---

## 👨‍💻 Author

**Alan Torres Fuentes**  
Developed as part of the **Alura Latam Challenge**  
Repository: [https://github.com/Torres-Alan/foro-hub-api.git](https://github.com/Torres-Alan/foro-hub-api.git)
