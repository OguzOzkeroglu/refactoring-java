# Refactoring Java

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