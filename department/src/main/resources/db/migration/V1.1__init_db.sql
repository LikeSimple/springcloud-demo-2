CREATE DATABASE IF NOT EXISTS springcloud_demo;

USE springcloud_demo;

CREATE TABLE IF NOT EXISTS department
(
    dept_id      INT PRIMARY KEY,
    dept_name    VARCHAR(50) NOT NULL UNIQUE,
    prev_dept_id INT
);

CREATE TABLE IF NOT EXISTS employee
(
    employee_id   INT PRIMARY KEY,
    employee_name VARCHAR(50) NOT NULL,
    dept_id       INT         NOT NULL
);