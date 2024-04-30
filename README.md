# PlaywrightApiAutomation

## Introduction
This repository contains automated API tests for a customer application.

### Technologies Used
- Java
- Maven
- Playwright framework with:
  - TestNG
  - jackson-databind
  - lombok
  - extentreports
  - Maven

## Getting Started
To run automated tests, ensure the following tools are installed:

- JDK version 17 or higher [Download JDK](https://www.oracle.com/java/technologies/downloads)
- Maven [Download Maven](https://maven.apache.org/download.cgi)
- Download all dependencies from [Maven Repository](https://mvnrepository.com/)

### Dependency Management
Configure Maven to automatically download dependencies specified in the `pom.xml` file.

## Run the WAR file
You can start the Jenkins Web Application Archive (WAR) file from the command line:

1. Download the latest Jenkins WAR file to an appropriate directory on your machine.
2. Open a terminal/command prompt window to the download directory.
3. Run the command `java -jar jenkins.war`.
4. You will get the message "Jenkins is fully up and running".
5. Browse to [http://localhost:8080](http://localhost:8080) and wait until the Unlock Jenkins page appears.

### IDE Setup
Intellij IDEA is used for automation test development. [Download Intellij IDEA](https://www.jetbrains.com/idea).

## Reference To Create a CRUD Operation
- Used the following site to perform API CRUD operations: [GoRest](https://gorest.co.in)

## Functional API Tests
For every new/updated application feature, a new test will be created or existing tests will be updated.

### Testing Best Practices
- Follow naming conventions for test classes and methods.
- Organize test suites logically.
- Ensure clear and descriptive assertions.

## To Obtain an Auth Token
- Create an account on [GoRest](https://gorest.co.in) using GitHub, Google, or Microsoft account.
- After creating an account, access the token via [Access Tokens](https://gorest.co.in/my-account/access-tokens).

## To Execute Tests
### Using Maven
- Run all functional tests with `mvn verify`. This executes all tests sequentially in one thread.

### Using Each Class
- Right-click on any class and select "Run Test".

### Using Complete Package
- Right-click on any package and select "Run Tests".

### Using Test Runners XML
- Right-click on `testng_regression.xml` and select "Run Tests".

## Contributing
To make changes in this repository:

1. Create a new branch from `master` and perform your changes.
2. Run tests to ensure nothing is broken.
3. Create a pull request and assign it to a tester/developer from your team or chapter.
4. Once the PR is approved and all tests are passing, it can be merged.

## Github Repository
- [Github Repository](https://github.com/sankarvid2020/CorporateCreditPlaywrightApiAutomation)

## Jenkins Pipelines
- [Jenkins Pipelines](http://localhost:8080/job/Corporate-Credit-PW-API-Automation/)

### Trigger Pipeline Manually
- Click on Build Now
- Once the pipeline is finished, go to click on "HTML Extend Report"