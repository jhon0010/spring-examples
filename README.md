# Java & Spring Boot Sample Project

This is a sample project designed to practice and explore Java and Spring Boot related technologies. Feel free to fork, clone, or contribute to this project.

## Table of Contents

1. [Technologies Used](#technologies-used)
2. [Getting Started](#getting-started)
3. [How to Run the Application](#how-to-run-the-application)
4. [Features](#features)
5. [Testing](#testing)
6. [Contributing](#contributing)
7. [License](#license)
8. [Contact](#contact)

## Technologies Used

- Java 1.8
- Spring Boot 2.5.2
- JUnit 5
- Mockito
- Maven/Gradle
- Postgres

## Getting Started

These instructions will help you get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 1.8 or higher
- Gradle
- Your preferred IDE (e.g., IntelliJ IDEA, Eclipse)
- Postgres

### Clone the Repository

```bash
git clone https://github.com/jhon0010/spring.git

git clone git@github.com:jhon0010/spring.git
```

## Before to run the app create the database for the flyway migration

Open a terminal and type:

`psql -U $user` by default postgres

`CREATE DATABASE order_management_system;`


# How to Run the Application

### Import the Project: Import the project into your IDE as Gradle project.

* Update Dependencies: Make sure to update/download the project dependencies.

* Configuration: Update application.properties or application.yml with your database settings.
* Run: Run the main application class Main.java.

Alternatively, you can build and run the project using the command line:

```bash
./gradlew build
java -jar build/libs/spring.jar
```

# Features -  Pending
Feature 1 (e.g., CRUD operations)
Feature 2 (e.g., Authentication)
Feature 3 (e.g., RESTful APIs)
...

# Testing

This project uses JUnit and Mockito for unit testing.

To run tests, execute:

```bash
./gradlew test
```

Or run tests using your IDE's built-in test runner.

# Contributing

Feel free to contribute to this project. Fork it, create new pull requests, or open new issues.

# License
This project is open-source and available under the MIT License.

# Contact
Jhon Lotero - jhoning.soft@gmail.com