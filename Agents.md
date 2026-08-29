# AGENTS.md

This file gives AI coding agents (Copilot, Claude, Cursor, Codex, etc.) the context needed to work productively in this repository.

## Project Overview

**PracticeFramework** is a Java-based UI test automation framework built on **Selenium WebDriver** and **TestNG**, managed with **Maven**. It follows a fairly standard Page-Object-style automation setup with reporting via **ExtentReports**, logging via **Log4j2**, and Excel-driven test data via **Apache POI**.

- **Language:** Java 8
- **Build tool:** Maven (`pom.xml`)
- **Test framework:** TestNG (`regression_testng.xml`)
- **Browser automation:** Selenium WebDriver 3.141.59 + WebDriverManager (auto driver binaries)
- **Reporting:** ExtentReports (HTML reports written to `reports/`)
- **Logging:** Log4j2 (logs written to `logs/`)
- **Data handling:** Apache POI (Excel-based test data / config)

## Repository Structure

```
.
├── src/                      # Main source: page objects, test classes, utilities, config readers
├── reports/                  # Generated ExtentReports HTML test reports (build artifact)
├── logs/                     # Generated Log4j2 log files (build artifact)
├── target/                   # Maven build output (build artifact — do not edit)
├── .settings/                # Eclipse IDE settings
├── .classpath, .project      # Eclipse project files
├── pom.xml                   # Maven dependencies, compiler & surefire config, build profiles
├── regression_testng.xml     # TestNG suite definition used for regression runs
└── README.md
```

> Note: `reports/`, `logs/`, and `target/` are generated output directories. Agents should not hand-edit files inside them — regenerate via the build/test commands below instead.

## Setup

```bash
# Requires JDK 8 and Maven installed
mvn clean install
```

This resolves dependencies (Selenium, TestNG, ExtentReports, POI, Log4j2, WebDriverManager, etc.) as declared in `pom.xml`.

## Build & Test Commands

```bash
# Compile the project
mvn clean compile

# Run the full regression suite (uses regression_testng.xml via the ChromeAutomation profile)
mvn clean test -PChromeAutomation

# Run a single test class
mvn test -Dtest=<TestClassName>

# Run a single test method
mvn test -Dtest=<TestClassName>#<testMethodName>
```

- The `ChromeAutomation` Maven profile wires up the Surefire plugin to execute `regression_testng.xml`.
- WebDriverManager handles browser driver binaries automatically at runtime — no manual chromedriver/geckodriver setup should be required.
- After a run, check `reports/` for the ExtentReports HTML output and `logs/` for Log4j2 execution logs when debugging failures.

## Code Conventions

- **Java 8** syntax/features only (per `maven-compiler-plugin` source/target `1.8`) — avoid var, records, switch expressions, and other newer Java syntax.
- Follow existing package structure under `src/` for new classes (e.g., keep page objects, test cases, and utility/config classes in their respective existing packages rather than introducing new top-level structures).
- Prefer the Page Object Model pattern for any new UI interactions — locators and page actions belong in page classes, not inline in test methods.
- Test classes should integrate with the existing TestNG + ExtentReports setup (i.e., reuse existing base test/listener classes rather than writing ad hoc reporting).
- Excel-based test data should go through the existing Apache POI utility classes rather than new parsing logic, if such utilities already exist in `src/`.

## Guidance for Agents

- **Before adding a dependency**, check `pom.xml` first — many common needs (Selenium, TestNG, POI, Log4j2, ExtentReports, WebDriverManager, JUnit, Hamcrest, Commons IO) are already included.
- **When adding a new test**, register it in `regression_testng.xml` so it's picked up by the regression run, matching the style of existing `<test>`/`<classes>` entries.
- **When modifying locators or page objects**, verify no other test classes depend on the same element/method signature before renaming.
- **Do not commit** contents of `target/`, `reports/`, or `logs/` — these are build/run artifacts.
- **Java version constraint:** since the compiler plugin targets Java 8, any suggested code must compile under Java 8.
- If Selenium/TestNG/WebDriverManager APIs seem ambiguous, prefer checking the exact pinned versions in `pom.xml` (Selenium 3.141.59, TestNG 7.1.0, WebDriverManager 4.0.0) rather than assuming latest-version API behavior, since these are notably older releases with different APIs than current versions.

## Known Gaps

- `README.md` currently has no project description — if updating it, keep this AGENTS.md and the README in sync on setup/run instructions.
- Dependency versions are dated (e.g., Selenium 3.x, TestNG 7.1.0); be cautious recommending upgrades without checking for breaking API changes across the codebase.
