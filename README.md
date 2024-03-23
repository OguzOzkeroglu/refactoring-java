# Refactoring Movie Rental Application

## Project Approach and Decisions
- **Branching Strategy**: This project was developed using a single branch, without the use of pull requests for merging changes. This approach was chosen as I am the sole contributor to the project, simplifying the development process.
- **Framework and Containerization**: To maintain the project's simplicity and to avoid overengineering, no additional frameworks or containerized support have been incorporated. The goal is to keep the project straightforward and focused on its core functionality.

## Refactoring Steps

### Step 1:
- Convert the project to a Maven project for better dependency management.
- Update the `.gitignore` file to exclude Maven and IDE-related files & folders.
- Add JUnit test dependencies for unit testing.

### Step 2:
- Incorporate the Maven JAR Plugin to enable building an executable JAR file.

### Step 3:
- Organize classes into packages to separate concerns and enhance maintainability.
    - `com.etraveli.movierental.models`: Contains domain models including `Customer`, `Movie`, and `MovieRental`.
    - `com.etraveli.movierental.services`: Contains services such as `RentalInfo` for handling business logic.
    - `com.etraveli.movierental`: Hosts the main application class `RentalApplication`

### Step 4:
- Refactored the `RentalApplication` main class for improved testability and readability.
  - Extracted the creation of test data into a separate method `createTestCustomer`.
  - Isolated the assertion logic into a method `assertExpectedResult` to simplify the main execution flow.

### Step 5:
- Introduced `MovieRepository` to manage movie data, simulating a database layer for better data management.
- Refactored `RentalService` to use `MovieRepository` for retrieving movie information, enhancing separation of concerns and making the service layer more focused on business logic.

### Step 6:
- Updated package names to singular form (e.g., `model`, `service`, `repository`) to adhere to common Java conventions.
- Made fields in model classes (`Customer`, `Movie`, `MovieRental`) final to enhance immutability and thread-safety.
- Introduced `MovieRepositoryTest` to validate the functionality of the movie data management layer, covering both successful retrievals and handling of non-existent movie IDs.
- Expanded `RentalServiceTest` with new test cases to cover edge cases, specifically:
  - Rental periods that border bonus conditions for new releases and additional charge thresholds for regular and children's movies.
  - Scenarios with customers having no rentals to ensure the service handles these cases gracefully.

### Step 7:
- Renamed the `statement` method in `RentalService` to `createRentalStatement` for enhanced clarity and readability. This name better reflects the method's purpose of generating a detailed rental statement for customers.

## Building and Running the Project:
Ensure you have Maven installed and configured properly to build and run this project.

#### To build the project and run the tests:
```
mvn clean package
```

#### To run the project:
```
java -jar target\refactoring-java-1.0-SNAPSHOT.jar
```