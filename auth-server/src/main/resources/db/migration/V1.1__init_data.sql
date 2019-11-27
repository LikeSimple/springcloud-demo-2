INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                  web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity,
                                  additional_information, autoapprove)
VALUES ('web_app', null, '{bcrypt}$2a$10$mIu6zvz50xnehwg0evLAXecy5IaM.UVHLY0KmN7Hae/45z94fUTwW', 'DEMO',
        'implicit,refresh_token,password,authorization_code', null, 'DEMO_READ,DEMO_WRITE', null, null, null, 'true');

INSERT INTO system_authority(id, name, description)
VALUES ('1o2heLqTl2X95qU8dNbrMJ', 'AUTH_ADMIN', 'ADMIN');

INSERT INTO system_role(id, name, description)
VALUES ('3wCcJ-ZMxbKUN6WLLj5jVy', 'ROLE_ADMIN', 'ADMIN ROLE');

INSERT INTO system_role_authority(role_id, authority_id)
VALUES ('3wCcJ-ZMxbKUN6WLLj5jVy', '1o2heLqTl2X95qU8dNbrMJ');

INSERT INTO system_user(id, username, password, account_expire, credential_expire)
VALUES ('2gsUKD5E9cqbE4AUqXkGZ_', 'test', '{bcrypt}$2a$10$qnlY7ym4Wza4sc7nYslOb.3eYt0/dyYMFdS/3Xj1ctSDh6MGq1YJq', NULL, NULL);

INSERT INTO system_user_role(system_user_id, role_id)
VALUES ('2gsUKD5E9cqbE4AUqXkGZ_', '3wCcJ-ZMxbKUN6WLLj5jVy');