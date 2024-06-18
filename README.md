**RestAssured API Automation using Java**

**Overview**
This project showcases automated testing of RESTful APIs using RestAssured library in Java. It covers various aspects of API automation including authentication mechanisms like OAuth 2.0 and OpenID Connect, handling multipart form data, serialization and deserialization of JSON using Jackson Object Mapper, request and response specifications, assertion using Hamcrest, and implementing design patterns like Builder and Singleton. The project is managed using Maven and integrates with Git for version control.


**Features**
RestAssured: Automated testing library for RESTful APIs.
Java: Programming language used for automation.
Postman Collections: Importing Postman collections for API testing scenarios.
Spotify Collections: Used spotify playlistAPI for the automation purpose.
JSON: Handling JSON payloads and responses.
Groovy GPath and Jayway JSONPath: Querying and asserting JSON responses.
Hamcrest Assertion: Asserting API responses using Hamcrest matchers.
Jackson Object Mapper: Serialization and deserialization of JSON objects.
Request Specification: Preparing API requests with common headers, cookies, and authentication.
Response Specification: Validating API responses against expected specifications.
Multipart Form Data: Handling file uploads and form data.
OAuth 2.0 and OpenID Connect: Implementing OAuth 2.0 and OpenID Connect for authentication.
Cookies and Headers: Managing cookies and headers in API requests.
Authorization Grant Flow: Implementing authorization flows like Authorization Code Grant.
Bearer Token: Authenticating APIs bearer tokens.
Lombok: To Reduce the boiler plate code
TestNG: For the test execution and work in parallel.
Builder Design Pattern: Creating complex objects using a builder pattern for readability and flexibility in POJO classes
Singleton Design Pattern: Ensuring a single instance of Properties file to load it once for the entire execution.
Automated Access Token Renewal: Automatically renewing access tokens during API requests.
Maven: Dependency management and build automation.
Integration with Git: Version control and collaboration using Git

**Prerequisites**
JDK 8 or higher installed
Maven installed
Git installed
Allure CLI (optional for generating and viewing Allure reports)

**Setup Instructions**
1. Clone the repository:
        git clone "https://github.com/zhunzargulhane/RestAssuredAPIAutomationAndFramework.git"

2. Install dependencies:
        Install all the dependencies present in the POM.xml file.
   
3. Run tests using Maven:
    mvn clean test -DBASE_URI="https://api.spotify.com"

4. Generate Allure reports:
        allure serve target/allure-reports

**Project Structure**

├── src
│   ├── main
│   │   └── java
│   │       └── com.spotify.auth2.pojo # This package includes the POJO classes with Builder design patter / LOMBOK
│   │   └── resources
│           └── payloads               # Here static playload is placed
│   │           
│   └── test
│       └── java
│           └── com.spotify.auth2.api
│               └── ApplicationAPI    # This includes application specific common reusable rest assured methods.
│               └── Utils
│                   ├── ConfigLoader  # To load the properties file only once and used single instance across framework
│                   ├── DataLoader    
│                   ├── FakerUtils    # To generate the randomData based on certain criteria
│                   ├── PropertyUtils  #To read the data from properties file 
│               └── tests              #Actual test test cases
│               └── RestResource   # Common rest assured methods
│               └── SpecBuilder    # Common cookies,headers, parameters to setup during API request
│               └── EnumStatusCode # This ENUM class stores the status code and description of it.
│               └── TokenManager   # This is used to renew the access token if not present
│               └── Route          # This class maintains the endpoints
│       └── resources
│           └── allure.properties
│           └── config.properties
│           └── data_loader.properties
├── pom.xml                           # Maven build configuration
└── README.md                         # This file

**Generating Allure Reports**
Allure automatically generates the report for all test cases.
Reports are generated in target/allure-reports and can be viewed using Allure CLI.

This README.md provides comprehensive information about setting up, usage of test automation framework.
