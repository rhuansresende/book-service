--liquibase formatted sql

--changeset rhuan.resende:v56-insert-user-master
INSERT INTO permission (description) VALUES
                                         ('ADMIN'),
                                         ('MANAGER'),
                                         ('COMMON_USER');

INSERT INTO users (
    user_name,
    full_name,
    password,
    account_non_expired,
    account_non_locked,
    credentials_non_expired,
    enabled
) VALUES
    ('03412808105', 'Rhuan Silva Resende', '{pbkdf2}7c7d035885d983c97ef22585a87d6cb3c4e500ddeaf9515e5bfd45398ca45d06c16213f61b85eb9a', true, true, true, true);

INSERT INTO user_permission (id_user, id_permission) VALUES
    (1, 1);