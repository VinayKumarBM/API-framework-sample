# API-framework-sample

## **Overview:**
API is the acronym for Application Programming Interface, which is a software intermediary that allows two applications to talk to each other.  This API framework is developed using REST Assured.  REST Assured is a Java library that provides a domain-specific language (DSL) for writing powerful, maintainable tests for RESTful APIs.

For Demo purpose all the test cases are created for [Swagger Petstore](https://petstore.swagger.io/) API.

### **Some of the key features of this framework:**

1. It generates Extent reports with all the step details.
2. It support parallel execution of API test cases.
3. It generates test execution log file.  Log files will generated in separate files in parallel runs.  So it is easy to go through logs in parallel run.
4. Test execution can be triggered form command line.
5. Easy integration to CI/CD pipeline.

## **Required Setup :**

- [Java](https://www.guru99.com/install-java.html) should be installed and configured.
- [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/) should be installed and configured.
- Download the files from Git repository either as zip file OR using [Git](https://phoenixnap.com/kb/how-to-install-git-windows).

## **Running Test:**

Open the command prompt and navigate to the folder in which pom.xml file is present.
Run the below Maven command.

    mvn verify

Once the execution completes report will be generated in below folder structure.

**Extent Report:** 	*/test-output/APIExecutionReport.html*
