# Mini Task Management System

A full-stack task management application built with:

- **Backend:** Spring Boot (JWT authentication + role-based authorization)
- **Frontend:** Next.js (App Router) + Axios
- **Database:** MySQL

## Project Overview

This application allows users to:

- Register and log in securely
- Manage tasks (create, view, update, delete)
- Filter and paginate task lists
- Use role-based access (`USER`, `ADMIN`)

## Project Structure

```text
taskmanagement/
├── src/                 # Spring Boot backend
├── frontend/            # Next.js frontend
├── pom.xml
└── README.md
```

## Tech Stack

### Backend
- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (`io.jsonwebtoken`)
- MySQL

### Frontend
- Next.js (App Router)
- React
- Axios

## Prerequisites

- Java 17+
- Maven
- Node.js 18+
- npm
- MySQL Server

## Environment Variables

Use environment variables for secrets and database config.

### Backend variables

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET`

Example mapping in `application.yaml`:

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

app:
  jwt:
    secret: ${JWT_SECRET}
```

## Database Setup

Create database:

```sql
CREATE DATABASE taskdb;
```

> If you use a different database name, update your datasource URL accordingly.

## How to Run the Application

### 1) Run Backend

From project root:

```bash
mvn spring-boot:run
```

Backend URL:

- `http://localhost:8080`

### 2) Run Frontend

From `frontend` folder:

```bash
npm install
npm run dev
```

Frontend URL:

- `http://localhost:3000` (or configured port)

## Authentication and Authorization

- JWT-based authentication
- Stateless security configuration
- Password hashing with BCrypt
- Role-based access control:
  - `USER`: manage own tasks
  - `ADMIN`: view all tasks

## API Documentation

### Auth APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and get JWT |

### Task APIs

| Method | Endpoint | Access |
|---|---|---|
| GET | `/api/tasks` | USER |
| POST | `/api/tasks` | USER |
| PUT | `/api/tasks/{id}` | USER |
| DELETE | `/api/tasks/{id}` | USER |
| GET | `/api/tasks/all` | ADMIN |

## Database Schema

### `users`

- `id` (PK)
- `name`
- `email` (unique)
- `password`
- `role` (`USER`/`ADMIN`)
- `created_at`

### `tasks`

- `id` (PK)
- `title`
- `description`
- `status` (`TODO`, `IN_PROGRESS`, `DONE`)
- `priority` (`LOW`, `MEDIUM`, `HIGH`)
- `due_date`
- `created_at`
- `updated_at`
- `user_id` (FK -> `users.id`)

## Features Implemented

- User registration and login
- JWT authentication
- Role-based authorization
- Task CRUD operations
- Pagination and sorting
- Input validation
- Global exception handling
- Frontend API integration with Axios
- Protected endpoints with Spring Security

## Notes

- Do not commit sensitive values (passwords, secrets) to source control.
- Use `.env` files (frontend) and environment variables (backend) for secrets.
- Do not commit generated folders like `node_modules` or build output directories.