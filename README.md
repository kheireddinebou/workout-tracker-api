# 🏋️‍♂️ Workout Tracker API

A robust, secure backend REST API built with **Spring Boot 4**, **PostgreSQL**, and **JWT authentication** designed for managing user accounts, cataloging exercises, scheduling workouts, logging performance metrics, and tracking fitness progress.

---

## 🛠️ Tech Stack

* **Language & Framework:** Java 21, Spring Boot 4.1.2 (Spring Framework 7)
* **Security & Auth:** Spring Security 7, JSON Web Tokens (JJWT 0.12.6)
* **Database & ORM:** PostgreSQL, Spring Data JPA / Hibernate
* **Containerization:** Docker & Docker Compose
* **Build Tool:** Gradle

---

## 🚀 Features

* **User Authentication & Authorization:** Secure sign-up, login, and stateless JWT-based session handling.
* **Exercise Catalog & Full CRUD:** Pre-seeded standard exercises (Bench Press, Squat, etc.) with full custom exercise management (`GET`, `POST`, `PUT`, `DELETE`).
* **Workout Management & Scheduling:** Create, update, delete, and schedule workouts. Attach custom comments and notes to your workout sessions.
* **Granular Performance Tracking:** Link workouts to multiple exercises with specific sets, repetitions, and weights lifted.
* **Progress Reporting:** Generate instant performance metrics calculating total workouts completed, total exercises performed, and total volume lifted.

---

## ⚙️ Getting Started (Local Development)

### Prerequisites
* Java 21 installed (`JAVA_HOME` configured)
* Docker and Docker Compose installed

### 1. Clone the Repository
```bash
git clone https://github.com/kheireddinebou/workout-tracker-api.git
cd workout-tracker-api
```

### 2. Configure Environment Variables
Create a .env file in the root directory (refer to .env.example):

```
Code snippet
DB_HOST=localhost
DB_PORT=5432
DB_NAME=workout_tracker_db
DB_USER=postgres
DB_PASSWORD=your_secure_password
JWT_SECRET=your_super_secret_jwt_key_at_least_256_bits
JWT_EXPIRATION=86400000
```


### 3. Spin Up PostgreSQL via Docker
```
Bash
docker compose up -d
```


### 4. Run the Application
```
Bash
./gradlew bootRun
```
