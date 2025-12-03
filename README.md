# midterm-second-project

Automated UI and database tests for a target web application using Java, Maven and TestNG/JUnit. The project uses a page object model for UI flows, step classes to organize test logic, and a MSSQL-backed data layer for test data preparation and verification. Tests can run locally via Maven or from IntelliJ IDEA and support cross-browser and mobile-emulation configurations.

## What this project does

- Implements end-to-end UI tests for search, locations, product details and global navigation.
- Uses page objects and step classes to keep test code readable and maintainable.
- Connects to a MSSQL database to insert, query and validate test data using configuration from `database.properties` and SQL scripts.
- Supports running tests with different browser and mobile-emulation profiles.

## Tech stack

- Java (JDK 11\+ recommended)
- Maven
- SQL (MSSQL)
- Test frameworks: TestNG / JUnit
- IDE: IntelliJ IDEA on Linux (recommended)

## Repository layout

- `pom.xml` \- Maven project file and dependencies

- `src/main/java` \- test helper and core automation code  
  - `ge/tbc/testautomation/pages`  
    - `BasePage.java` \- common WebDriver/page utilities  
    - `SearchPage.java`, `LocationPage.java`, `SectionPage.java`, `MobileNavigationPage.java` \- page objects for application screens  
  - `ge/tbc/testautomation/steps`  
    - `BaseSteps.java` \- shared step utilities  
    - `SearchSteps.java`, `LocationFilterSteps.java`, `ProductDetailsSteps.java`, `GlobalNavigationSteps.java`, `DefaultMapSteps.java` \- high-level steps used by tests  
  - `ge/tbc/testautomation/data`  
    - `MSSQLConnection.java` \- low-level JDBC connection helper for MSSQL  
    - `DBConfiguration.java` \- loads DB properties from `database.properties`  
    - `DatabaseSteps.java` \- DB-related test operations (queries, inserts, validations)  
    - `Constants.java`, `LocationConstants.java` \- shared constants for tests  
    - `DataSupplier.java`, `LocationDataProvider.java` \- data providers for parameterized tests  
  - `ge/tbc/testautomation/utils` \- (reserved for utility classes, if any)

- `src/main/resources` \- configuration and SQL scripts  
  - `database.properties` \- MSSQL connection properties (host, port, DB name, user, password, etc.)  
  - `MainScript.sql` \- main DB schema and seed data script  
  - `Locations.sql` \- supplemental data for locations

- `src/test/java` \- tests and runners  
  - `ge/tbc/testautomation/runners`  
    - `BaseTest.java` \- base test setup (WebDriver, configuration, common hooks)  
  - `ge/tbc/testautomation/tests`  
    - `SearchTest.java` \- tests for search functionality  
    - `LocationTest.java` \- tests for location filtering and map\-related flows  
    - `ProductDetailsTest.java` \- tests for product details pages  
    - `GlobalNavigationTest.java` \- tests for top\-level navigation

- Root\-level aux files  
  - `cross-browser.xml` \- configuration/profile for cross\-browser execution  
  - `mobile-emulation.xml` \- configuration/profile for mobile\-emulation runs  
  - `expected_snapshot.txt` \- reference snapshot for visual or content comparison

- `target/` \- Maven build output (compiled classes, test\-classes, generated sources, reports)

## Prerequisites

- Java JDK 11\+ (or the version defined in `pom.xml`)
- Maven 3\.6\+  
- Running MSSQL Server instance reachable from your machine
- Correct DB credentials configured in `src/main/resources/database.properties`
- IntelliJ IDEA (or another Java IDE) on Linux

## Setup

1. Clone the repository:
   ```bash
   git clone git@github.com:anatota/midterm-second-project.git
   cd midterm-second-project
   ```

2. Configure the database connection:  
   Edit `src/main/resources/database.properties` and set:
    - DB host and port
    - DB name
    - Username and password
    - Any required JDBC options (e.g., SSL, timeouts)

3. Prepare the database (if not already prepared):  
   Run these scripts against your MSSQL instance:
    - `src/main/resources/MainScript.sql`
    - `src/main/resources/Locations.sql`

4. Import into IntelliJ IDEA (optional but recommended):
    - Open IntelliJ IDEA
    - `File \> Open...` and select the `pom.xml`
    - Let IntelliJ import it as a Maven project

## Build and run tests

From the project root:

- Clean and run the full test suite:
  ```bash
  mvn clean test
  ```

- Run a specific test class (for example, `SearchTest`):
  ```bash
  mvn -Dtest=SearchTest test
  ```

- Run a specific test method:
  ```bash
  mvn -Dtest=SearchTest#testMethodName test
  ```

- Use a specific Maven profile (if you define any profiles in `pom.xml`):
  ```bash
  mvn -P<profile-name> clean test
  ```

In IntelliJ IDEA:

- Right\-click a test class (for example, `src/test/java/ge/tbc/testautomation/tests/SearchTest.java`) and select `Run` to execute only that class.
- Right\-click the `tests` package to run all tests in that package.
- Use or create run configurations to customize JVM options, system properties, or test filters.

## Configuration

- Database configuration is controlled by:
    - `src/main/resources/database.properties`
    - `ge/tbc/testautomation/data/DBConfiguration.java`
    - `ge/tbc/testautomation/data/MSSQLConnection.java`

- Cross\-browser and mobile\-emulation settings:
    - `cross-browser.xml`
    - `mobile-emulation.xml`

Update these files to match your local environment or CI configuration.

## Test reports and artifacts

- Maven Surefire/Failsafe reports are generated under:
    - `target/surefire-reports`
    - `target/failsafe-reports` (if integration tests are configured)

Review these reports for detailed failure information and stack traces.

## Contributing

1. Create a branch from `main` for your change:
   ```bash
   git checkout -b feature/short-description
   ```
2. Make your changes and add/update tests as needed.
3. Run `mvn clean test` to ensure all tests pass.
4. Open a pull request with a clear description of the changes.

## License

License information is not explicitly defined in this repository.  
For questions, contact GitHub user `anatota`.
