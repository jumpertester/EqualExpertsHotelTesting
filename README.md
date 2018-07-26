## Equal Experts Hotel Automation (Chris Warren) ##

Welcome to my Equal Experts Hotel automation project.

**Pre Requisites & Assumptions**

- It is assumed the application will be run on a Windows or iOS platform.
- The tests are configured to run in Chrome which must be installed in its default location.
- The application has been compiled using Java SDK 1.8.0_111.
- This is a Maven project and has been created using version 3.3.9.

**Running the Tests (Windows)**

Inside the root directory is the runTest.bat file. Executing this file will perform an mvn clean install which will download all dependencies needed and execute the tests. On completion the target folder will have been created inside the root directory.

**Running the Tests (Mac)**

Open the Terminal.

Navigate to the directory containing the projects pom.xml file.

Run the command 'mvn clean install'

When executed the dependencies will be downloaded and the tests executed. The target directory will be created within the test directory.


**Test Results**

On completion of running the tests navigate from the root directory to target/surefire-reports and open the file emailable-report.html in the browser of your choice.

    