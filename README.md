# SauceDemo Automation Framework

A production-ready Java Selenium Cucumber automation framework for SauceDemo using Maven and Page Object Model (POM).

## Tech Stack

- Java 17
- Selenium WebDriver
- Cucumber BDD
- TestNG
- Maven
- Page Object Model (POM)
- Excel-driven test data support

## Framework Structure

- `src/main/java/com/saucedemo/pages`
- `src/main/java/com/saucedemo/utils`
- `src/main/java/com/saucedemo/config`
- `src/test/java/com/saucedemo/stepdefinitions`
- `src/test/java/com/saucedemo/runners`
- `src/test/resources/features`
- `src/test/resources/testData`
- `src/test/resources/fixtures`
- `reports`
- `screenshots`

## Setup

1. Install Java 17 and Maven.
2. Clone or copy the repository.
3. Copy `.env.example` to `.env` and update values.
4. Run tests with:

```bash
mvn clean test
```

## GitHub Actions

The workflow is configured in `.github/workflows/ci.yml` and executes `mvn clean test` on every push.

## Notes

- Selectors are encapsulated in page classes.
- Explicit waits are used across reusable page methods.
- Screenshots are captured on failure.
- Retry logic is enabled for failed TestNG executions.
- Excel support is implemented via Apache POI.
