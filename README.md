
# Stay Ease

## Problem Statement
Develop and deploy a RESTful API service using Spring Boot to streamline the room booking process for a hotel management aggregator application. You are required to use MySQL to persist the data.

## Problem Description
Please note that this is a simplified version of an online room booking system, and you should focus on implementing the specified features effectively within the given constraints
You can make the following assumptions:
 The application only has a single type of room and all bookings are for two guests
Any hotel manager can update any hotel details i.e you do not have to keep track of who manages which hotel
Another service handles check-in and check-out functionalities
The service must implement authentication and authorization
The service uses JWT tokens for session management
The service must have three roles: CUSTOMER, HOTEL MANAGER, and ADMIN
The service must have two types of API endpoints:
Public endpoints - Anyone can access (Ex. Registration, Login)
Private endpoints - Only authenticated users can access (Ex. Book a room)

Note: Some of the design choices are left to you. All design decisions such as designing the database schema, and providing resource access based on roles must have a thorough thought process behind them.




 






## Tech Stack

**Database:** MySql

**Server:** Spring Boot

**Dependencies:** Spring web, Lombok, Validator, Spring Data Jpa, MySql Connector, Spring boot starter security,JJWT-impl,JJWT-jackson,JJWT-api


## Problem Link

 - [Problem description](https://docs.google.com/document/d/1cXOf1pbn1L79MpVZqX90jgGnPGqvsf0KgJsyxH8JT0U/edit)



## Authors

- [@Utkarsh Kumar Verma](https://github.com/Utkarshkverma)


## Referances

- [Spring boot with MySql](https://spring.io/guides/gs/accessing-data-mysql)
- [Spring data mapping](https://docs.spring.io/spring-data/relational/reference/jdbc/mapping.html)
- [Spring boot data validation](https://www.baeldung.com/spring-boot-bean-validation)
- [Postman Guide](https://learning.postman.com/docs/getting-started/overview/)
- [Spring security](https://docs.spring.io/spring-security/reference/index.html)

## API's Documentations

- For the documentations and details of API's [Click Here](https://documenter.getpostman.com/view/32128600/2sA3rwMtyx) 


