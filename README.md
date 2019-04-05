# Goodreads-like-Java-Enterprise-Project
An Java EE enterprise application using JPA/EJB/Restful Services/MySql database. Developed in eclipse and wildfly 10.

## What has been done : 
- Created the following Scenario in a DB Schema
	- User, Books, Reviews, UserActionLogs. User has Book preferences, Book has Reviews from Users, User has Reviews for Books, Every change (add, edit, delete) is put on the UserActionLog.

- Created the following Rest Services
	- User registration with email (use BCrypt to hash password in DB)
	- User Login (security with Basic Auth) and return a JWT Token
	- EDIT/DELETE User
	
## What will be done in the future : 
  Rest Services :
	- GET user reviews (of a single user)
	- GET/ADD/EDIT/DELETE Books (any authorized user can do that)
	- GET Book Reviews (of a single book)
	- ADD/EDIT/DELETE a Review from an authorized User to a Book
