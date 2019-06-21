drop index idx_system_user_username on system_user;
create unique index idx_system_user_username
    on system_user (username);