# 🏦 Loan Application Management API

This is a Spring Boot-based RESTful application developed for managing loan applications, built as part of a Java backend assessment.

---

## 🚀 Features

- Submit and update loan applications
- Retrieve loan records with pagination
- Validate applicants by credit score (mock external API)
- Logs all HTTP requests and responses
- Data initialization using `data.sql`
- No Lombok – constructor-based injection
- Uses `WebClient` for external API calls
- Enum mapping for loan status (`PENDING`, `APPROVED`, `REJECTED`)

---

## 🧱 Architecture & Design Patterns

| Concept                     | Implementation |
|----------------------------|----------------|
| **Architecture**           | Layered (Controller → Service → Repository) |
| **Dependency Injection**   | Constructor-based injection (manual, no Lombok) |
| **Persistence**            | Spring Data JPA |
| **External API Call**      | `WebClient` (non-blocking, clean abstraction) |
| **DTO Usage**              | Used to separate domain and API layers |
| **Enum Mapping**           | JPA Enum (`@Enumerated(EnumType.STRING)`) |
| **Filter**                 | Custom Servlet Filter for request/response logging |
| **Logging**                | SLF4J with log file at `C:/loan-sytem-logs/loan-app.log` |
| **Database Initialization**| Via `data.sql` on app start |

---

## ⚙️ Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- WebClient (Spring Reactive Web)
- MySQL 8
- SLF4J (simple file logging)
- Docker (for DB container)
- Maven

---

## 📦 Setup & Run

### ✅ Prerequisites
- Java 17+
- Maven 3.2+
- MySQL 8+ (or Docker)

### 🐳 Start MySQL using Docker (Optional)
```bash
docker run --name loan-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=testdb -p 3306:3306 -d mysql:8
