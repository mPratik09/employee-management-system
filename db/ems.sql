DROP DATABASE employee_department_system;

create database employee_department_system;

use employee_department_system;

create table employees (
    id int primary key AUTO_INCREMENT,
    firstname varchar(25) not null,
    lastname varchar(25),
    email varchar(50) unique not null,
    password varchar(255) not null,
    contactnum varchar(50) not null,

    createdat timestamp default CURRENT_TIMESTAMP,
    updatedat timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

