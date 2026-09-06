# Phoenix API Automation Framework

A scalable and maintainable **REST API Automation Framework** built using Java, REST Assured, TestNG, Maven, and Allure.

The framework is designed to support API automation with reusable services, request/response models, data-driven testing, schema validation, database validation, logging, retry mechanisms, and CI/CD execution.

---

## Overview

Phoenix API Automation Framework provides a structured approach to automate REST APIs while keeping test cases simple, readable, and maintainable.

## Key Capabilities

- REST API automation using REST Assured
- Test execution using TestNG
- Maven-based project management
- Reusable API service layer
- Request and response POJOs
- Data-driven testing
- JSON, CSV, and Excel test data support
- Dynamic test data generation using Java Faker
- JSON Schema validation
- Database validation using MySQL
- Authentication and token management
- Log4j2 logging
- Retry mechanism for transient failures
- Allure test reporting
- Environment-based configuration
- GitHub Actions CI/CD support

---

## Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming language |
| Maven | Build and dependency management |
| REST Assured | REST API automation |
| TestNG | Test execution |
| Jackson | JSON serialization and deserialization |
| JSON Schema Validator | API response validation |
| Apache POI | Excel data handling |
| OpenCSV | CSV data handling |
| Java Faker | Dynamic test data |
| MySQL | Database validation |
| HikariCP | Database connection pooling |
| Log4j2 | Logging |
| Allure | Test reporting |
| GitHub Actions | CI/CD |

---

## Project Structure

```text
Phoenix-API-Automation-Framework/
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── api/
│       │   │   ├── constant/
│       │   │   ├── filters/
│       │   │   ├── request/
│       │   │   ├── response/
│       │   │   ├── services/
│       │   │   ├── test/
│       │   │   └── utils/
│       │   │
│       │   ├── database/
│       │   │   ├── dao/
│       │   │   ├── model/
│       │   │   └── DataBaseManager.java
│       │   │
│       │   ├── dataproviders/
│       │   ├── listeners/
│       │   └── retryanalyzer/
│       │
│       └── resources/
│           ├── config/
│           ├── schemaValidator/
│           ├── testData/
│           └── log4j2.xml
│
├── .env.example
├── .gitignore
├── pom.xml
├── testng.xml
├── testng-datadriven.xml
└── README.md
