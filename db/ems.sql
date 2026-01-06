DROP DATABASE if exists employee_department_system;

create database employee_department_system;

use employee_department_system;

create table users (
    id int primary key AUTO_INCREMENT,
    first_name varchar(25) not null,
    last_name varchar(25),
    email varchar(50) unique not null,
    password varchar(255) not null,
    contact_num varchar(50) not null,
    `status` varchar(10) DEFAULT 'PENDING',
    `role` varchar(10) DEFAULT NULL,
    createdat timestamp default CURRENT_TIMESTAMP,
    updatedat timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

create table departments (
	id int primary key auto_increment,
    dept_code VARCHAR(20) unique,
	department varchar(50) not null
);

SET SESSION sql_mode = CONCAT(@@sql_mode, ',NO_AUTO_VALUE_ON_ZERO');
insert into departments (id, dept_code, department) values
	(0, "UA_00", "Unassigned"),
	(1,  "SUPPORT_01", "Support"),
	(2,  "HR_02", "Human Resources"),
    (3, "IT_03", "Information Technology"),
    (4,  "FIN_04", "Finance");

create table emp_depart (
	emp_id int,
    depart_id int,
    primary key (emp_id, depart_id),
    foreign key (emp_id) references users(id),
	foreign key (emp_id) references departments(id)
);


-- ------------ QUERIES --------------

-- users table inserts

SET SESSION sql_mode = CONCAT(@@sql_mode, ',NO_AUTO_VALUE_ON_ZERO');
insert into users (id, first_name, last_name, email, password, contact_num, status, role) 
		values(0, "Nebula", "support", "support@nebula.co.in", "{noop}Support@123", "9087654321", "ASSIGNED", "SUPPORT"),
        (1, "Aman", "Chandra", "aman@nebula.co.in", "{noop}Aman@123", "9090909001", "PENDING", NULL),
        (2, "Atharva", "Kolape", "atharva@nebula.co.in", "{noop}Atharva@123", "9090909004", "PENDING", NULL),
        (3, "Amit", "Bavalekar", "amit@nebula.co.in", "{noop}Amit@123", "9090909004", "PENDING", NULL),
        (4, "Arjun", "Deshmukh", "arjun@nebula.co.in", "{noop}Arjun@123", "9090909005", "PENDING", NULL),
        (5, "Akshay", "Bhoite", "akshay@nebula.co.in", "{noop}Akshay@123", "9090909006", "PENDING", NULL),

        (6, "Sanket", "Pimplikar", "sanket@nebula.co.in", "{noop}Sanket@123", "9090909011", "PENDING", NULL),
		(7, "Somnath", "Kumbhar", "somnath@nebula.co.in", "{noop}Somnath@123", "9090909012", "PENDING", NULL),
		(8, "Sahil", "Dabhade", "sahil@nebula.co.in", "{noop}Sahil@123", "9090909013", "PENDING", NULL),
		(9, "Sushil", "Dabhade", "sushil@nebula.co.in", "{noop}Sushil@123", "9090909014", "PENDING", NULL);
