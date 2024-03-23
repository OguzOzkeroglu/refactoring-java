# Refactoring Java

### Step 1:
- Convert project to a Maven project
- Update .gitignore file to ignore Maven and IDE related files & folders
- Add JUnit test dependencies

### Step 2:
- Add maven jar plugin to be able to run the project after build

## To build the project and run the tests:
```
mvn clean package
```

## To run the project:
```
java -jar target\refactoring-java-1.0-SNAPSHOT.jar
```