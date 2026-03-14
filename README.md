# Midterm Second Project

Automation test project for the TBC Bank website built with Java, Playwright, TestNG, Maven, and MS SQL Server.

This repository focuses on maintainable UI automation: page objects for selectors, step classes for reusable business flows, data providers for parameterized coverage, and TestNG suites for cross-browser and mobile execution. The project was developed on Linux Ubuntu, with MS SQL Server running in Docker as part of the local setup.

## What This Project Demonstrates

- Playwright-based UI automation in Java.
- Page Object Model with a separate step layer to keep tests readable.
- Parameterized TestNG tests using both static and database-backed data providers.
- Cross-browser execution for Chromium, Firefox, and WebKit.
- Mobile-specific validation using a dedicated TestNG suite and mobile viewport setup.
- Geolocation-based testing for map and branch/ATM functionality.
- Snapshot-style accessibility validation with Playwright ARIA snapshots.
- Working with Georgian-language UI content and assertions.

## Covered Test Scenarios

The current suite covers four main areas:

- Search: validates results for multiple valid keywords and empty-state behavior for invalid ones.
- Global navigation: checks mega menu behavior, breadcrumb correctness, and mobile hamburger navigation.
- Locations: validates default map state, geolocation-aware marker behavior, invalid searches, and filtered branch results.
- Product details: captures and compares an ARIA snapshot for the money transfers page.

## Architecture

The project is organized around a layered test design:

- `pages/`: page object classes that store locators and page-specific element loading methods.
- `steps/`: higher-level reusable actions and assertions built on top of page objects.
- `tests/`: TestNG test classes that express scenarios in a concise way.
- `data/`: constants, configuration, JDBC connection setup, and TestNG data providers.
- `resources/`: database configuration and SQL scripts used for test data setup.

This structure keeps selectors, behavior, and test intent separated, which makes the suite easier to extend and maintain.

## Tech Stack

- Java
- Maven
- Playwright for Java
- TestNG
- Microsoft SQL Server JDBC Driver
- SQL Server

## Project Structure

```text
.
├── pom.xml
├── cross-browser.xml
├── mobile-emulation.xml
├── expected_snapshot.txt
├── src
│   ├── main
│   │   ├── java/ge/tbc/testautomation
│   │   │   ├── data
│   │   │   ├── pages
│   │   │   └── steps
│   │   └── resources
│   │       ├── database.properties
│   │       ├── Locations.sql
│   │       └── MainScript.sql
│   └── test/java/ge/tbc/testautomation
│       ├── runners
│       └── tests
└── README.md
```

## Key Implementation Details

### Test Runner

`BaseTest` creates Playwright, launches the requested browser, and configures either desktop or mobile context based on TestNG parameters from suite XML files.

### Reusable Test Design

Tests are intentionally short because most interaction logic lives in step classes such as:

- `SearchSteps`
- `GlobalNavigationSteps`
- `LocationFilterSteps`
- `DefaultMapSteps`
- `ProductDetailsSteps`

This makes the test layer easier to read and better aligned with real user flows.

### Data-Driven Testing

The project uses two data sources:

- Static TestNG data providers in `DataSupplier` for search and breadcrumb scenarios.
- SQL-backed location test data in `LocationDataProvider`, which reads values from `location_cases`.

### Cross-Browser and Mobile Coverage

- `cross-browser.xml` runs desktop scenarios against Firefox, Chromium, and WebKit.
- `mobile-emulation.xml` runs selected scenarios with mobile viewport settings.

### Database Integration

Database access is configured through:

- `src/main/resources/database.properties`
- `DBConfiguration`
- `MSSQLConnection`

The location suite uses SQL Server data to drive test execution. In the local Linux Ubuntu environment for this project, the database runs in Docker, which keeps setup isolated and reproducible.

## Prerequisites

Before running the suite, make sure you have:

- Java installed
- Maven installed
- Docker installed if you want to mirror the same Linux Ubuntu-based database setup
- SQL Server running locally or accessible from your machine
- Playwright browser dependencies available in your environment

## Setup

1. Clone the repository.

```bash
git clone git@github.com:anatota/midterm-second-project.git
cd midterm-second-project
```

2. Configure database access in `src/main/resources/database.properties`.

Example:

```properties
jdbc.url=jdbc:sqlserver://localhost:1433;databaseName=LOCATIONS_DB;trustServerCertificate=true;
jdbc.username=Real_User
jdbc.password=RealUser123#
```

3. Start MS SQL Server.

On Linux Ubuntu, this project uses Docker to run the SQL Server instance locally.

4. Prepare the SQL Server data used by the project.

- `src/main/resources/MainScript.sql`
- `src/main/resources/Locations.sql`

5. Install project dependencies and Playwright requirements in your local environment if they are not already available.

## Running Tests

Run the full suite:

```bash
mvn test
```

Run one test class:

```bash
mvn -Dtest=SearchTest test
```

Run a single test method:

```bash
mvn -Dtest=GlobalNavigationTest#megaMenuElementsTest test
```

Run the desktop cross-browser suite:

```bash
mvn test -Dsurefire.suiteXmlFiles=cross-browser.xml
```

Run the mobile suite:

```bash
mvn test -Dsurefire.suiteXmlFiles=mobile-emulation.xml
```

## Snapshot Testing

`ProductDetailsTest` works with `expected_snapshot.txt` and compares the page body against an ARIA snapshot.

This is useful for catching structural regressions in important UI content without relying only on visual checks.

## Notes

- The suite currently launches browsers in non-headless mode and uses `slowMo` in `BaseTest`, which is useful for local debugging. For production-level running in headless mode, comment the corresponding line in BaseTest.java (// remove this) and un-comment the next line.
- Some suite XML entries are intentionally commented out, so the XML files act as configurable execution templates.
- The project includes database utility classes beyond the currently active test flows, which suggests room for expanding DB validation coverage.

## Skills Highlighted

This project highlights practical automation engineering skills:

- test architecture instead of one-file automation scripts
- maintainable abstraction layers
- multi-browser execution
- mobile-aware testing
- data-driven design
- integration between UI automation and backend test data

## Author

Ana Totadze
