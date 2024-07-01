# Shipmnts-Task2

Youtube Demo: [https://shipmnts.kevintamakuwala.me/](https://shipmnts.kevintamakuwala.me/)
## Tech Stack Used:
- Springboot
- AWS EC2, RDS, Nginx, Certbot
## Functionalities Implemented

### Books Webscraping

- Used jsop dependency for Web scrapping.
- Used @Schedular to Scheduling Scrapping. At each schedule new Books are inserted in DB. 

## Setup Instructions

### Prerequisites

Before setting up Project, ensure you have the following prerequisites installed:

- **Java Development Kit (JDK)**: Required for running the Spring Boot backend.
- **MySQL Database**: Database management system for storing application data.
- **IDE (Integrated Development Environment)**: Recommended IDEs include IntelliJ IDEA or VS Code for backend and frontend development.

### Backend Setup (Spring Boot)

1. **Clone the repository**:

   ```
   git clone https://github.com/kevintamakuwala/Shipmnts-Task2.git
   ```

2. **Import the backend project** into your preferred IDE (e.g., IntelliJ IDEA).

3. **Configure the database connection**:
   
   - Open the `application-dev.properties` file located in `backend/src/main/resources`.
   - Update the database connection properties such as URL, username, and password according to your MySQL configuration.

4. **Build and run the Spring Boot application**:
   
   - Run the main class `BackendApplication.java` to start the Spring Boot server.


### Accessing Project

Once both the backend and frontend are running, you can access ByteBattles by navigating to the provided URLs in your web browser:

- **Backend**: [http://localhost:8000](http://localhost:8000)
