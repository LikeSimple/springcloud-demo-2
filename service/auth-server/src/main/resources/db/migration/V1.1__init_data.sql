INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                  web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity,
                                  additional_information, autoapprove)
VALUES ('web_app', null, '{bcrypt}$2a$10$mIu6zvz50xnehwg0evLAXecy5IaM.UVHLY0KmN7Hae/45z94fUTwW', 'DEMO',
        'implicit,refresh_token,password,authorization_code', null, 'DEMO_READ,DEMO_WRITE', null, null, null, 'true');