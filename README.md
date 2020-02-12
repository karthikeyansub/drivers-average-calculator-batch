# drivers-average-calculator-batch
F1 race drivers average report generate service

# Input and Output file path setup

  Go to /src/main/resources folder and change the input/output and log file path in the applicaion environment properties.

  Copy & paste the file "/input/drivers-data.csv" in the input folder location

  Steps to build and execute the batch application

# STEP #1: set classpath for windows using following command

> set path=%path%;<<java_installed_path>>

# STEP #2: Go to the POM file location and build the package

> mvn package

# STEP #3: Go to /target folder where the package is generated and start the application using below command,

> java -jar -Dspring.profiles.active=dev drivers-average-calculator-batch-0.0.1-SNAPSHOT.jar


