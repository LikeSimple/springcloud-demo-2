CREATE TABLE IF NOT EXISTS corporation
(
    corp_id      INT NOT NULL auto_increment,
    corp_name    VARCHAR(50) NOT NULL,
    CONSTRAINT pk_corp PRIMARY KEY (corp_id),
    INDEX idx_corp_name (corp_name)
);

CREATE TABLE IF NOT EXISTS department
(
    dept_id      INT NOT NULL auto_increment,
    dept_name    VARCHAR(50) NOT NULL UNIQUE,
    prev_dept_id INT DEFAULT NULL,
    corp_id      INT NOT NULL,
    CONSTRAINT pk_dept PRIMARY KEY (dept_id),
    CONSTRAINT fk_prev_dept FOREIGN KEY (prev_dept_id) REFERENCES department (dept_id) ON DELETE RESTRICT,
    CONSTRAINT fk_corp FOREIGN KEY (corp_id) REFERENCES corporation (corp_id) ON DELETE RESTRICT,
    INDEX idx_dept_name (dept_name)
);

CREATE TABLE IF NOT EXISTS employee
(
    employee_id   INT NOT NULL auto_increment,
    employee_name VARCHAR(50) NOT NULL UNIQUE,
    dept_id       INT         NOT NULL,
    corp_id       INT         NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (employee_id),
    CONSTRAINT fk_employee_department FOREIGN KEY (dept_id) REFERENCES department (dept_id) ON DELETE RESTRICT,
    CONSTRAINT fk_employee_corporation FOREIGN KEY (corp_id) REFERENCES corporation (corp_id) ON DELETE RESTRICT,
    INDEX idx_employee_name (employee_name)
);