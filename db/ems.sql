DROP DATABASE employee_department_system;

create database employee_department_system;

use employee_department_system;

create table users (
    id int primary key AUTO_INCREMENT,
    first_name varchar(25) not null,
    last_name varchar(25),
    email varchar(50) unique not null,
    password varchar(255) not null,
    contact_num varchar(50) not null,
    `employee` boolean default false,
    `admin` boolean default false,
    createdat timestamp default CURRENT_TIMESTAMP,
    updatedat timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

create table departments (
	id int primary key auto_increment,
    dept_code VARCHAR(20) unique,
	department varchar(50) not null
);

-- insert into departments values(
--     1, "HR_01", "Human Resources",
--     2, "IT_02", "Information Technology",
--     3, "FIN_03", "Finance",
--     4, "SUPPORT_04", "Support"
-- );

insert into departments (dept_code, department) values
	( "HR_01", "Human Resources"),
    ( "IT_02", "Information Technology"),
    ( "FIN_03", "Finance"),
    ( "SUPPORT_04", "Support");


create table emp_depart (
	emp_id int,
    depart_id int,
    primary key (emp_id, depart_id),
    foreign key (emp_id) references users(id),
	foreign key (emp_id) references departments(id)
);


-------------- QUERIES --------------

-- users table inserts

SET SESSION sql_mode = CONCAT(@@sql_mode, ',NO_AUTO_VALUE_ON_ZERO');
insert into users (id, first_name, last_name, email, password, contact_num) 
		values(0, "Nebula", "support", "support@nebula.co.in", "Support@123", "9087654321");
