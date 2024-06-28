# API Automation Framework

This is an API automation framework built using Java, Maven, RestAssured, and TestNG.

## Prerequisites

- Java 8 or higher
- Maven 3.6.0 or higher
- TestNG
## How to Run 
- Add all maven dependency from pom.xml
- Run testng.xml file

## Reporting 
- TestNg report -> to generate in intellij -> Select configuration -> Select TestNG -> Listeners Tick Use default reporters

## Project Structure

├───.idea
├───src
│   ├───main
│   │   └───java
│   │       └───pojo
│   └───test
│       └───java
│           ├───resources
│           └───testCase
├───target
│   ├───classes
│   │   └───pojo
│   ├───generated-sources
│   │   └───annotations
│   ├───generated-test-sources
│   │   └───test-annotations
│   └───test-classes
│       ├───resources
│       └───testCase
└───test-output
├───All Test Suite
├───junitreports
└───old
└───All Test Suite

