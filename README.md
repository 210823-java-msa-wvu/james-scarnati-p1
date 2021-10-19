# james-scarnati-p1
# TRMS (Tuition Reimbursement Management System)
- Create a website which allows users to submit a tuition reimbursement application that will be approved by the employees supervisors and management personel
# Technologies used
- JavaScript
- HTML
- CSS
- Java 8
- JDBC
- Servlets
- AJAX HTTP Requests
- AWS RDS
- Apache Tomcat 9

# Features
- Login/Logout
- Submit a request
- Check available funds
- Approve a request
- Request more information from employee or supervisors
- View requests and approval status
#
**Todo List**
- Make the tabled requests selectable to make requesting information and the approval process easier
- Implement the ability to upload a file
- Implement an alert function to alert the user to pending tasks waiting for them upon log-in
- Restructure the table in the database to simplify the program

# Getting Started
**git clone https://github.com/210823-java-msa-wvu/james-scarnati-p1.git**
- create an AWS RDS 
**Create Tables**
- create an employee table:
  - create table employee { id serial, fname varchar, lname varchar, username varchar unique, pass varchar, title varchar, startingbalance float, pendingbalance float, usedbalacne float}
- create courses table
  - create table courses { id serial, course varchar, reimbursementpercent float}
- create requests table
  - create table requests { id serial, employeeid int, requestdate varchar, coursestart varchar, passinggrade varchar, coursecost float, reimbursementamount float, coursetype int, approval boolean, pass boolean, deny boolean, grade varchar, dmcheck boolean, file varchar, urgent boolean, hours int, location varchar, reason varchar, complete boolean }
- create approval table:
  - create table approval { id serial, dsapproval boolean, denialreason varchar, dhapproval boolean, bcapproval boolean, inforequeste boolean, inforequestds boolean, inforequestdh boolean, employeeinfo varchar, dsinfo varchar, dhinfo varchar, denied boolean }
#
**Add courses**
- insert into courses values (1, "University Course", .8),(2, "Seminar", .6),(3, "Certification Preperation Class", .75),(4, "Certification", 1),(5, "Technical Training", .9),(6, "Other", .3)
#
**Add Employees**
- insert into employee values (default, "Jane", "Hill", "BenCo", "pass", "Benefits Coordinator", 1000, 0, 0),(default, "Kyle", "Skor", "DS", "pass", "Direct Supervisor", 1000, 0, 0),(default, "Howard", "White", "DH", "pass", "Department Manager", 1000, 0, 0),(default, "Josh", "Grant", "Employee", "pass", "Employee" , 1000, 0, 0),(default, "Nathan", "Colt", "DM", "pass", "Direct Manager", 1000, 0, 0)

# Usage
Once downloaded and the tables are built, all that should be neccessary to run the project is to run the program using Apache Tomcat 9 and open a browser and go to http://localhost:8080/login.html
