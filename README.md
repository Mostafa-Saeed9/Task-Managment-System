          Task Management Application
A comprehensive Task Management Application designed to simplify task assignment, tracking, and notification workflows.
This application is built using Spring Boot for the backend and integrates security, authentication, and notification features.

        Features
1. User Authentication and Authorization
    Secure Login: Implements JWT-based authentication.
    Role-Based Access Control:
    ADMIN Role: Access to all administrative endpoints and features.
    USER Role: Access to user-specific endpoints and tasks.
    Passwords are securely hashed using BCrypt.
   
3. Task Management
    Create, update, and delete tasks.
    Assign tasks to specific users.
    Track task progress and deadlines.
    Fetch tasks filtered by deadlines or users.
   
4. Email Notifications
    Scheduled Email Alerts:
    Notifications for upcoming task deadlines.
    Important task-related updates.
    Configurable email service using SMTP or third-party integrations (e.g., SendGrid).
    Fully customizable HTML email templates.
   
5. Security
    JWT Authentication:
    Generate secure tokens for user sessions.
    Validate tokens for every request.
    Spring Security-based access control for routes.
    Integrated with a JwtAuthenticationFilter for seamless authentication.
   
6. Modular Design
  Highly modular codebase for ease of maintenance and scalability.
  Separation of concerns between authentication, task management, and notifications.

7. API Endpoints
    Authentication
    POST /auth/login: Authenticate users and generate JWT tokens.
    Task Management
    GET /tasks: Retrieve all tasks (requires authentication).
    POST /tasks: Create a new task (Admin only).
    PUT /tasks/{id}: Update a task (Admin only).
    DELETE /tasks/{id}: Delete a task (Admin only).
    Notifications
    Scheduled job to send email reminders for tasks nearing deadlines.
   
9. Technology Stack
    Backend: Spring Boot (Java)
    Security: Spring Security with JWT
    Database: JPA with Hibernate and Mysql
    Email Service: JavaMailSender with SMTP configuration
    


Project Structure

src/
├── main/
│   ├── java/
│   │   ├── banquemisr/
│   │   │   ├── controller/       # Controllers for API endpoints
│   │   │   ├── entity/            # Entity classes
│   │   │   ├── dto/            # dto classes
│   │   │   ├── enim/            # enum 
│   │   │   ├── exception/            # custom exception
│   │   │   ├── repository/       # JPA repositories
│   │   │   ├── security/         # Security configuration and filters
│   │   │   ├── service/          # Business logic and services
│   │   │   ├── scheduler/        # Scheduled tasks for email notifications
│   ├── resources/
│   │   ├── application.properties # Application configuration





      Setup Instructions
1. Clone the Repository
        https://github.com/Mostafa-Saeed9/Task-Managment-System.git 
        cd task-management
2. Build the Project
       mvn clean install
3. Run the Application
      mvn spring-boot:run





