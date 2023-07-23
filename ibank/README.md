# Getting Started

### Reference Documentation
Ibank Application

Ibank application is developed in Java-8 ,Spring Boot and MySQL as web-application without UI.
This application has the following end points.

##To Start
Download Ibank project from Github.
Run maven compilier once and build the project.
Start IbankApplication.java and project will be up.

To setup data:
First create data in Accounts, Beneficiary, Transactions and AccountBalance table through queris or respective end points.

##For Accounts
http://localhost:8080/api/aibank/v1/accounts/create
http://localhost:8080/api/ibank/v1/accounts/update
http://localhost:8080/api/ibank/v1/accounts/getAccount/{id}

Request body for RestAPI call:
{
    "accountName" : "Abraham",
    "phone" : "1234567890",
    "email": "abraham@gmail.com",
    "status" : "active"
}

Query:
insert into accounts (account_id,account_name,email,phone,status)
values
(1,'Abraham','abraham@gmail.com',3434343434,'active'),
(2,'Joshi Joshi','joshi.joshi@gmail.com',4545454545,'active');

##For Beneficiary
http://localhost:8080/api/ibank/v1/beneficiary/create
http://localhost:8080/api/ibank/v1/beneficiary/update
http://localhost:8080/api/ibank/v1/beneficiary/delete

Request body for RestAPI call:
{
    "accountId" : 123456,
    "beneficiaryAccountId" : "3434343434",
    "beneficiaryIFSCCode" : "IFSC890890",
    "beneficiaryName" : "David David",
    "status" : "Active" 

}

## For transaction
For start transaction from one account to beneficiary account
http://localhost:8080/ibank/v1/transact

Request body for RestAPI call:
{
    "fromAccountId" : 123456,
    "toAccountId" : 3434343434,
    "transactionAmount": 1000

}

For withdraw and deposit amount

http://localhost:8080/ibank/v1/withdraw?accountId=123456&amount=3000
http://localhost:8080/ibank/v1/deposit?accountId=123456&amount=3000




