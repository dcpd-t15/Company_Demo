# Company Demo

This is a Spring Boot application demonstrating a company management system with Departments, Employees, and Projects. It uses MySQL as the database, Spring Data JPA for persistence, Lombok for boilerplate reduction, and provides RESTful APIs. Jackson annotations are used to handle entity serialization and prevent circular reference issues.

## Features
- CRUD operations for Departments, Employees, and Projects
- Relationship management between entities
- Service layer for business logic
- Maven build system

## Prerequisites
Before you begin, ensure you have the following installed:
- **Java 17** (or compatible JDK)
- **Maven** (latest version recommended)
- **MySQL** (8.0 or later)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)
- **Git** (for cloning the repository)

## Project Structure
```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── controller/    - REST controllers
│   │   ├── entity/       - JPA entities with Jackson annotations
│   │   ├── repository/   - JPA repositories
│   │   ├── service/      - Business logic
│   │   └── DemoApplication.java - Main application class
│   └── resources/
│       └── application.properties - Configuration file
pom.xml - Maven configuration
```

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/company-management-system.git
cd company-management-system
```

### 2. Configure MySQL
1. Install MySQL if not already installed
2. Create a new database:
   ```sql
   CREATE DATABASE company_db;
   ```
3. Update the database credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/company_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
   Replace `your_username` and `your_password` with your MySQL credentials.

### 3. Install Dependencies
Run the following command in the project root to download all Maven dependencies:
```bash
mvn clean install
```

### 4. Run the Application
#### Using Maven:
```bash
mvn spring-boot:run
```
#### Using IDE:
1. Import the project as a Maven project
2. Run `DemoApplication.java` as a Java application

The application will start on `http://localhost:8080`.

### 5. Test the APIs
Use a tool like Postman or curl to test the endpoints. Here are some examples:

#### Create a Department
```
POST http://localhost:8080/departments
Content-Type: application/json
{
    "name": "IT"
}
```

#### Create an Employee
```
POST http://localhost:8080/employees
Content-Type: application/json
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "department": {"id": 1}
}
```

#### Get All Employees
```
GET http://localhost:8080/employees
```

#### Assign Project to Employee
```
POST http://localhost:8080/employees/1/projects/1
```

## API Endpoints
| Method | Endpoint                       | Description                    |
|--------|--------------------------------|-------------------------------|
| GET    | /departments                  | List all departments          |
| GET    | /departments/{id}            | Get department by ID          |
| POST   | /departments                 | Create a department           |
| DELETE | /departments/{id}            | Delete a department           |
| POST   | /departments/{id}/projects/{projectId} | Assign project to department |
| GET    | /employees                   | List all employees            |
| GET    | /employees/{id}             | Get employee by ID            |
| POST   | /employees                  | Create an employee            |
| DELETE | /employees/{id}             | Delete an employee            |
| POST   | /employees/{id}/projects/{projectId} | Assign project to employee |
| GET    | /projects                    | List all projects             |
| GET    | /projects/{id}              | Get project by ID             |
| POST   | /projects                   | Create a project              |
| DELETE | /projects/{id}              | Delete a project              |
| POST   | /projects/{id}/employees/{employeeId} | Assign employee to project |

## Sample Response
```json
{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "department": {
        "id": 1,
        "name": "IT",
        "employees": [],
        "projects": []
    },
    "project": {
        "id": 1,
        "name": "Project A",
        "description": "Description",
        "employees": []
    }
}
```

## Troubleshooting
- **Database connection error**: Verify MySQL is running and credentials are correct
- **Port 8080 in use**: Change the port in `application.properties` by adding `server.port=8081`
- **Dependencies not downloading**: Run `mvn clean install -U` to force update
- **Infinite JSON response**: Ensure `@JsonManagedReference` and `@JsonBackReference` are correctly applied to entity relationships

