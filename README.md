
# Learning Navigator


## Table of Contents

1. [Problem Statement](#problem-statement)
2. [Problem Description](#Problem-Description)
3. [Technologies](#technologies)
4. [Setup](#setup)
5. [API Endpoints](#APIEndpoints)
6. [Additional Notes](#Additional-Notes)




# Problem Statement

Develop a RESTful API service using Spring Boot to manage the exam enrollment process for a Learning Management System (LMS). You are required to use MySQL to persist the data.

# Problem Description

The exam registration service is a critical component of a Learning Management System. Generally, exam registration requires thorough Authentication and Authorization. For this assessment, your task is to develop a simplified version of the exam registration service that meets the specified requirements below.

## Technologies

- Java 21
- Spring Boot 3.3.0
- Spring Data JPA
- MySQL
- Lombok
- Gradle-Groovy

## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/LearningNavigator.git
   cd LearningNavigator

2.Configure MySQL Database:

- Create a MySQL database named learning_navigator.

- Update the application.properties file with your MySQL database connection details.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/exam
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```
3. **Build the project:**
 
  ```bash
  ./gradlew build
```
4. **Run the application:**
  ```bash
  ./gradlew bootRun
```
5. **Access the application:**

-  The application will be running at http://localhost:8088.

# API Endpoints

### Easter Egg Controller

**GET /hidden-feature/{number}**

- Fetch a trivia fact about the specified number from the Numbers API.

**Response Example:**

```json
{
  "fact": "42 is the answer to the ultimate question of life, the universe, and everything."
}
```
### Exam Controller:

**GET /exams** 
- Get all exams.

**GET /exams/{examId}** 
- Get an exam by ID.

**POST /exams** 
- Create a new exam.
```json
{
  "examId": "Ex01"
}
```

**PUT /exams/{examId}** 
- Update an existing exam.
```json
{
  "examId": "Ex001"
}
```

**DELETE /exams/{examId}** 
- Delete an exam.

**POST /exams/{examId}/subjects/{subjectId}** 
- Register a subject for an exam.

### Student Controller:

**GET /students** 
- Get all students.

**GET /student/{studentId}** 
- Get a student by ID.

**POST /students** 
- Create a new student.
```json
{
  "registrationId": "ST01",
  "name": "Swanand"
}
```

**PUT /students/{studentId}** 
- Update an existing student.
```json
{
  "registrationId": "ST101",
  "name": "Harshal"
}
```

**DELETE /students/{studentId}** 
- Delete a student.

**POST /students/{studentId}/register-exam/{examId}** 
- Register a student for an exam.

**POST /students/{studentId}/enroll-subject/{subjectId}** 
- Enroll a student for a subject.


### Subject Controller:

**GET /subjects** 
- Get all subjects.

**GET /subjects/{subjectId}** 
- Get a subject by ID.

**POST /subjects** 
- Create a new subject.
```json
{
  "subjectId": "SUB01",
  "name": "Mathematics"
}
```

**PUT /subjects/{subjectId}** 
- Update an existing subject.

**DELETE /subjects/{subjectId}** 
- Delete a subject.
Additional Notes:



## Additional Notes:
- Make sure you have configured your MySQL database with the correct credentials as specified in the `application.properties` file.
- Ensure that no other application is already running on port 8088 to avoid conflicts.
- For production deployment, consider building a deployable JAR or WAR file using `./gradlew build` and deploying it to your preferred server environment.
- These steps should allow you to build and run the Learning Navigator project locally on your machine. Adjustments may be necessary based on your specific development environment and requirements.
