#BOOK LIBRARY APPLICATION
This is a book library application built with spring boot. The core dependencies 
used are:
1. spring web.
2. spring jpa,
3. spring security
4. h2 db

#HOW TO RUN THE APPLICATION
The application uses an in-memory db, so there will be no need to configure any database
connection. To run the applicaiton:
1. pull the code from git
2. run: mvn clean package
3. run: mvn spring-boot:run

Immediately the application is run successfully, a default user will be created. His credentials are:
* email: tom@gmail.com
* password: password
Use the credentials to login. After login, a token will be generated (which is a bearer token). 
This should be used to consume all the other apis on the application.

Without authentication, none of the apis will be accessible.

Also, swagger documentation has been configured on the application, so after running the application 
successfully, hit 'http://localhost:9094/swagger-ui.html' to view all the api documentations of the code. 
You can also use the swagger-ui to test the apis (or postman depending on your preference).