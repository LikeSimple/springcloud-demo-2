create table newtouch.system_authority
(
    id char(22) not null primary key,
    name varchar(40) not null,
    description varchar(200) null,
    created_time date default CURRENT_TIMESTAMP not null,
    modified_time date null,
    constraint uni_system_authority_name
        unique (name)
);
comment on table newtouch.system_authority is '系统权限';

create table newtouch.system_role
(
    id char(22) not null
        primary key,
    name char(40) not null,
    description varchar(200) null,
    created_time date default CURRENT_TIMESTAMP not null,
    modified_time date null,
    constraint uni_system_role_name
        unique (name)
);
comment on table newtouch.system_role is '系统角色';

create table newtouch.system_role_authority
(
    role_id char(22) not null,
    authority_id char(22) not null,
    created_time date default CURRENT_TIMESTAMP not null,
    primary key (role_id, authority_id),
    constraint fk_sra_system_role
        foreign key (role_id) references newtouch.system_role (id),
    constraint fk_sra_system_authority
        foreign key (authority_id) references newtouch.system_authority(id)
);
comment on table newtouch.system_role_authority is '系统角色权限';

create table newtouch.system_user
(
    id char(22) not null
        primary key,
    username varchar(30) not null,
    password char(68) not null,
    enabled CHAR(1) default '1' not null,
    locked CHAR(1) default '0' not null,
    account_expire date null,
    credential_expire date null,
    created_time date default CURRENT_TIMESTAMP not null,
    modified_time date null,
    constraint uni_system_user_username
        unique (username)
);
comment on table newtouch.system_user is '系统管理员';

create table newtouch.system_user_role
(
    system_user_id char(22) not null,
    role_id char(22) not null,
    created_time date default CURRENT_TIMESTAMP not null,
    primary key (system_user_id, role_id),
    constraint fk_sur_system_user
        foreign key (system_user_id)
            references newtouch.system_user (id),
    constraint fk_sur_system_role
        foreign key (role_id)
            references newtouch.system_role(id)
);
comment on table newtouch.system_user_role is '系统管理员角色';