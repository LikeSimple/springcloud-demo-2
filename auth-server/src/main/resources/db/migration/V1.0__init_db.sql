CREATE TABLE IF NOT EXISTS oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256),
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(256),
    INDEX idx_ocd_client_id (client_id)
) COMMENT 'OAuth2注册用户表';

CREATE TABLE IF NOT EXISTS system_authority
(
    id CHAR(22) NOT NULL PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(200) NULL,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_time DATETIME NULL,
    CONSTRAINT uni_system_authority_name
        UNIQUE (name)
) COMMENT '系统权限';

CREATE TABLE IF NOT EXISTS system_role
(
    id CHAR(22) NOT NULL
        PRIMARY KEY,
    name CHAR(40) NOT NULL,
    description VARCHAR(200) NULL,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_time DATETIME NULL,
    CONSTRAINT uni_system_role_name
        UNIQUE (name)
) COMMENT '系统角色';

CREATE TABLE IF NOT EXISTS system_role_authority
(
    role_id CHAR(22) NOT NULL,
    authority_id CHAR(22) NOT NULL,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (role_id, authority_id),
    CONSTRAINT fk_sra_system_role
        FOREIGN KEY (role_id) REFERENCES system_role (id),
    CONSTRAINT fk_sra_system_authority
        FOREIGN KEY (authority_id) REFERENCES system_authority(id)
) COMMENT '系统角色权限';

CREATE TABLE IF NOT EXISTS system_user
(
    id CHAR(22) NOT NULL
        PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    password CHAR(68) NOT NULL,
    enabled CHAR(1) DEFAULT '1' NOT NULL,
    locked CHAR(1) DEFAULT '0' NOT NULL,
    account_expire DATETIME NULL,
    credential_expire DATETIME NULL,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_time DATETIME NULL,
    CONSTRAINT uni_system_user_username
        UNIQUE (username)
) COMMENT '系统管理员';

CREATE TABLE IF NOT EXISTS system_user_role
(
    system_user_id CHAR(22) NOT NULL,
    role_id CHAR(22) NOT NULL,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (system_user_id, role_id),
    CONSTRAINT fk_sur_system_user
        FOREIGN KEY (system_user_id)
            REFERENCES system_user (id),
    CONSTRAINT fk_sur_system_role
        FOREIGN KEY (role_id)
            REFERENCES system_role(id)
) COMMENT '系统管理员角色';

