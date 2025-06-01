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


## 📂 API Endpoints

| HTTP Method | Endpoint               | Description                        | Access         |
|-------------|------------------------|------------------------------------|----------------|
| `POST`      | `/api/loans`           | Create new loan application        | Authenticated  |
| `GET`       | `/api/loans/{id}`      | Get loan by ID                     | Authenticated  |
| `GET`       | `/api/loans`           | List loans with pagination         | Authenticated  |
| `PUT`       | `/api/loans/{id}`      | Update loan application            | Role: `ADMIN`  |
| `GET`       | `/health`              | Health check                       | Public         |
| `GET`       | `/actuator/**`         | Spring Boot actuator endpoints     | Public         |

---

### 📤 Sample JSON Request (POST /api/loans)
{
  "applicantName": "Vishesh",
  "applicantIdNumber": "ID100",
  "amount": 10000,
  "termInMonths": 12,
  "status": "PENDING"
}


### 📤 Sample JSON Response (POST /api/loans)
{
    "id": 5,
    "applicantName": "Vishesh",
    "applicantIdNumber": "ID100",
    "amount": 2000.00,
    "termInMonths": 12,
    "status": "DISBURSED",
    "creditScore": 717
}

---


## Setup Instructions (For Developers)

✅ 1. Clone the repository

git clone https://github.com/vish-mzn/Loan-System.git

cd Loan-System


✅ 2. Start MySQL (using Docker)

docker run --name mysql8 \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=testdb \
  -p 3306:3306 \
  -d mysql:8

✅ 3. Build and Run

mvn clean install
mvn spring-boot:run



---

## 🌐 External Properties

### ✅ Mock External Credit Score API (Mockoon)

To simulate the third-party credit score API during local development, this project uses [Mockoon](https://mockoon.com/), a lightweight mock server.

Make sure to start the mock server using Mockoon with the expected endpoint running on port `9000`.

**Property to set in `application.properties`:**

third.party.url=http://localhost:9000/api/mockcredit



---

## 📬 Postman Collection

A Postman collection is provided to help developers quickly test and explore the Loan Application Management APIs.

📁 **Location**:  
`postman-collection/LoanSystem.postman_collection.json`

### 🔧 How to Use

1. Open Postman
2. Click **Import**
3. Select the file:  
   `postman-collection/LoanSystem.postman_collection.json`
4. Set environment variables if needed (e.g., `baseUrl = http://localhost:9090`)
5. Run requests such as:
   - `POST /api/loans`
   - `GET /api/loans`
   - `PUT /api/loans/{id}` (requires Basic Auth with role `ADMIN`)

This collection includes authentication headers and request bodies to help you get started immediately.



