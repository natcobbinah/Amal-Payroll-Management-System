########################################
#Author: Nathaniel Cobbinah
#Payroll Database and related queries
########################################

########################################
#Create database payrollusermanagement
########################################
create database payrollusermanagement;

########################################
#Use payrollusermanagement
########################################
use payrollusermanagement;

########################################
#create table users
########################################
create table users(
	id int primary key auto_increment,
    name varchar(100),
    email varchar(100) unique,
    password varbinary(20),
    enabled boolean,
    address varchar(100),
    city varchar(100),
    phonenumber varchar(50) unique,
    employeeid varchar(50) unique,
    employeelevel varchar(100),
    gender varchar(50),
    birthdate  date,
    hiredate date,
    maritalstatus varchar(50),
    bankaccountnumber varchar(50) unique,     
	birthcertid varchar(50) unique,
    driverslicenseid varchar(50) unique,
    votersid varchar(50) unique,
    passportid varchar(50) unique,
    ssnitid varchar(50) unique
)engine =InnoDb;



########################################
#create table roles
########################################
create table roles(
	roleid int primary key auto_increment,
    rolename varchar(50) unique
)engine =InnoDB;


########################################
#create table userroles
########################################
create table userroles(
	id int primary key auto_increment,
	userid int,
    roleid int,
    foreign key(userid) references users(id) on delete set null,
    foreign key(roleid) references roles(roleid) on delete set null
)engine = InnoDB;

########################################
#create table department
########################################
create table department(
	id int primary key auto_increment,
    departmentid varchar(50) unique,
    departmentname varchar(100)
)engine = InnoDB;

########################################
#create table userdepartment
########################################
create table userdepartment(
	id int primary key auto_increment,
    userid int,
    deptid int,
	foreign key(userid) references users(id) on delete set null,
    foreign key(deptid) references department(id) on delete set null
)engine = InnoDB;	

select * from users;
select * from roles;
select * from userroles;
select * from department;
select * from userdepartment;
