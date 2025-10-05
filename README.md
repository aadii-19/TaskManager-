# TaskManager - Spring Boot REST API

A comprehensive task management system built with Spring Boot, featuring user management, project organization, and task tracking with full CRUD operations.

## 🚀 Features

- **User Management**: Registration, authentication, and role-based access
- **Project Organization**: Create and manage projects with ownership
- **Task Tracking**: Assign tasks with status, priority, and deadlines
- **Relationship Management**: Proper entity relationships with cascade operations
- **Data Validation**: Comprehensive input validation and error handling
- **Audit Trail**: Automatic timestamp tracking for all entities

## 🛠️ Tech Stack

- **Backend**: Spring Boot 3.x
- **Database**: MySQL 8.0+
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Java Version**: 17+
- **Libraries**: Lombok, Jackson, Spring Validation

## 📊 Database Schema

### Users Table
- `id` (Primary Key)
- `firstName`, `lastName`
- `email` (Unique)
- `password`
- `role` (USER, ADMIN)
- `createdAt`, `updatedAt`

### Projects Table
- `id` (Primary Key)
- `name`, `description`
- `user_id` (Foreign Key → users.id)
- `createdAt`, `updatedAt`

### Tasks Table
- `id` (Primary Key)
- `title`, `description`
- `status` (PENDING, IN_PROGRESS, COMPLETED)
- `priority` (LOW, MEDIUM, HIGH)
- `deadline`
- `project_id` (Foreign Key → project.id)
- `assigned_to` (Foreign Key → users.id)
- `createdAt`, `updatedAt`

## 🔗 API Endpoints

### User Management
POST /api/users # Create new user
GET /api/users # Get all users
GET /api/users/{id} # Get user by ID
PUT /api/users/{id} # Update user
DELETE /api/users/{id} # Delete user (cascades to projects/tasks)

text

### Project Management
POST /api/users/{userId}/projects # Create project
GET /api/users/{userId}/projects # Get user's projects
GET /api/users/{userId}/projects/{projectId} # Get specific project
PUT /api/users/{userId}/projects/{projectId} # Update project
DELETE /api/users/{userId}/projects/{projectId} # Delete project

text

### Task Management (Coming Soon)
POST /api/projects/{projectId}/tasks # Create task
GET /api/projects/{projectId}/tasks # Get project tasks
GET /api/projects/{projectId}/tasks/{taskId} # Get specific task
PUT /api/projects/{projectId}/tasks/{taskId} # Update task
DELETE /api/projects/{projectId}/tasks/{taskId} # Delete task

text

## ⚙️ Setup & Installation

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Configuration

1. **Clone the repository**
git clone https://github.com/aadii-19/TaskManager-.git
cd TaskManager-

text

2. **Database Setup**
CREATE DATABASE taskdb;

text

3. **Configure application.properties**
spring.application.name=taskManager
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

text

4. **Run the application**
mvn spring-boot:run

text

5. **Access the API**
- Base URL: `http://localhost:8080`
- Test endpoints using Postman or curl

## 📝 Usage Examples

### Create a User
POST http://localhost:8080/api/users
Content-Type: application/json

{
"firstName": "John",
"lastName": "Doe",
"email": "john.doe@example.com",
"password": "securePassword123",
"role": "USER"
}

text

### Create a Project
POST http://localhost:8080/api/users/1/projects
Content-Type: application/json

{
"name": "Mobile App Development",
"description": "Building a React Native app for task management"
}

text

## 👨‍💻 Author

**Aadii** - [@aadii-19](https://github.com/aadii-19)

## 🔮 Roadmap

- [ ] Complete Task CRUD operations
- [ ] Add Spring Security authentication
- [ ] Implement role-based authorization
- [ ] Add comprehensive unit tests
- [ ] Create API documentation with Swagger

---

⭐ Star this repository if you found it helpful!
