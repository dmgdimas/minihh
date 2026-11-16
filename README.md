# Mini-HH — Job Board API

REST API для публикации и поиска вакансий. Пет-проект для отработки Spring Boot.

## Tech Stack
- Java 21
- Spring Boot 4.1.2 (Web, Data JPA, Security)
- PostgreSQL
- Maven, Lombok

## How to run
1. Clone the repo
2. Configure PostgreSQL in `application.properties`
3. `mvn spring-boot:run`

## API Endpoints
| Method | URL             | Description           |
|--------|-----------------|-----------------------|
| GET    | /api/vacancies  | List all vacancies    |

## Roadmap
- [x] Project skeleton
- [ ] Vacancy CRUD
- [ ] PostgreSQL + JPA
- [ ] JWT authentication
- [ ] Docker