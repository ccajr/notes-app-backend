# 📝 Note-Taking API

A simple RESTful API built with Spring Boot that allows users to create, retrieve, update, and delete notes. Each note contains a title and a body.

---

## 🚀 Getting Started

### Prerequisites

- Java 17
- Spring Boot 3.5.5
- Maven

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/ccajr/notes-app-backend.git
   cd notes-app-backend
   ```
2. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
3. **Access the API**
   ```
   http://localhost:8080/notes
   ```
> 💡 If port `8080` is already in use, you can change it in `src/main/resources/application.properties`:
> ```properties
> server.port=9090
> ```

---

## 📌 API Endpoints

| Method | Endpoint        | Description              |
|--------|-----------------|--------------------------|
| POST   | `/notes`        | Create a new note        |
| GET    | `/notes`        | Retrieve all notes       |
| GET    | `/notes/{id}`   | Retrieve note by ID      |
| PUT    | `/notes/{id}`   | Update note by ID        |
| DELETE | `/notes/{id}`   | Delete note by ID        |
