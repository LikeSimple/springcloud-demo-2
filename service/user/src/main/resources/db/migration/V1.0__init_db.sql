CREATE TABLE IF NOT EXISTS system_user
(
    id                CHAR(22) PRIMARY KEY,
    username          VARCHAR(30) NOT NULL,
    password          VARCHAR(80) NOT NULL,
    enabled           TINYINT(1)  NOT NULL DEFAULT 1,
    locked            TINYINT(1)  NOT NULL DEFAULT 0,
    account_expire    DATETIME,
    credential_expire DATETIME,
    INDEX idx_system_user_username (username)
);