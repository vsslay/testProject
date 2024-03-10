# Test task project

## Table of Contents

- [Run the project](#run-the-project)
- [Reporting](#reporting)
- [Configuration](#configuration)


## Run the project

1. #### Chrome:

   ```bash
   mvn test -Dbrowser=chrome -Dheadless=false

2. #### Chrome headless:

   ```bash
   mvn test -Dbrowser=chrome -Dheadless=true

3. #### Edge:

   ```bash
   mvn test -Dbrowser=edge -Dheadless=false

4. #### Edge headless:

   ```bash
   mvn test -Dbrowser=edge -Dheadless=true

5. #### Firefox:

   ```bash
   mvn test -Dbrowser=firefox -Dheadless=false

## Reporting
To generate allure report, after testrun launch:

   ```bash
   allure serve
   ````


To clean test results launch:
   ```bash
   rm -Force -r ./allure-results/*
   ````


## Configuration
Test run can be configured with **_browser_** and **_headless_** variables

Available options for **_browser_** variable:
````
1. -Dbrowser=chrome
2. -Dbrowser=edge
3. -Dbrowser=firefox
````
* Available options for **_headless_** variable:
````
1. -Dheadless=true
2. -Dheadless=false
````

