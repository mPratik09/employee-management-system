DROP DATABASE if exists employee_department_system;

create database employee_department_system;

use employee_department_system;

CREATE TABLE `status` (
   `id` int not null auto_increment primary key,
   `status` varchar(10) not null unique
);

create table users (
    id int primary key AUTO_INCREMENT,
    first_name varchar(25) not null,
    last_name varchar(25),
    email varchar(50) unique not null,
    password varchar(255) not null,
    contact_num varchar(50) not null,
    `status` varchar(10) default 'UNASSIGNED',
    createdat timestamp default CURRENT_TIMESTAMP,
    updatedat timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    constraint fk_users_status foreign key (status) references status (status)
);

create table departments (
	id int primary key auto_increment,
    depart_code varchar(20) not null unique,
    department varchar(25) not null unique,
    landing_page varchar(25) not null,
    allowed_view varchar(25) not null
);

SET SESSION sql_mode = CONCAT(@@sql_mode, ',NO_AUTO_VALUE_ON_ZERO');
insert into departments (id, depart_code, department, landing_page, allowed_view) values
	(1, "ADM_01", "ADMIN", "admin_dashboard", "ALL_USERS"),
	(2,  "SPRT_02", "Support", "support_dashboard", "PENDING_USERS"),
	(3,  "HR_03", "Human Resources", "hr_portal", "HR_USERS"),
    (4, "IT_04", "Information Technology", "it_console", "TECH_USERS"),
    (5,  "FIN_05", "Finance", "finance-board", "FINANCE_USERS");

create table emp_depart (
	emp_id int,
    depart_id int,
    primary key (emp_id, depart_id),
    foreign key (emp_id) references users(id),
	foreign key (depart_id) references departments(id)
);

SET SESSION sql_mode = CONCAT(@@sql_mode, ',NO_AUTO_VALUE_ON_ZERO');
insert into `status` (id, `status`) values (0, 'UNASSIGNED'), (1, 'PENDING'), (2, 'APPROVED'), (3, 'REJECTED');

-- ------------ QUERIES --------------

-- users table inserts

SET SESSION sql_mode = CONCAT(@@sql_mode, ',NO_AUTO_VALUE_ON_ZERO');
insert into users (id, first_name, last_name, email, password, contact_num, `status`) 
		values(0, "Nebula", "Support", "support@nebula.co.in", "{noop}Support@123", "9087654321", "APPROVED"),
        (1, "Aman", "Chandra", "aman@nebula.co.in", "{noop}Aman@123", "9090909001", "UNASSIGNED"),
        (2, "Amit", "Bavalekar", "amit@nebula.co.in", "{noop}Amit@123", "9090909004", "UNASSIGNED"),
        (3, "Akshay", "Bhoite", "akshay@nebula.co.in", "{noop}Akshay@123", "9090909006", "UNASSIGNED"),
        (4, "Arjun", "Deshmukh", "arjun@nebula.co.in", "{noop}Arjun@123", "9090909005", "UNASSIGNED"),
        (5, "Atharva", "Kolape", "atharva@nebula.co.in", "{noop}Atharva@123", "9090909004", "UNASSIGNED"),
        (6, "Atul", "Ghag", "atul@nebula.co.in", "{noop}Atul@123", "9090909004", "UNASSIGNED"),

        (7, "Sanket", "Pimplikar", "sanket@nebula.co.in", "{noop}Sanket@123", "9090909011", "UNASSIGNED"),
		(8, "Somnath", "Kumbhar", "somnath@nebula.co.in", "{noop}Somnath@123", "9090909012", "UNASSIGNED"),
		(9, "Sahil", "Dabhade", "sahil@nebula.co.in", "{noop}Sahil@123", "9090909013", "UNASSIGNED"),
		(10, "Sushil", "Dabhade", "sushil@nebula.co.in", "{noop}Sushil@123", "9090909014", "UNASSIGNED"),
        
        (11, "Jairam", "Kamat", "jairam@nebula.co.in", "{noop}Jairam@123", "9090909013", "APPROVED");

insert into emp_depart values(0, 2), (11, 3);