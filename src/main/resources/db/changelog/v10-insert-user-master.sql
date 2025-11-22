--liquibase formatted sql

--changeset rhuan.resende:v56-insert-user-master

INSERT INTO permission (description) VALUES
                                         ('ADMIN'),
                                         ('MANAGER'),
                                         ('COMMON_USER');

INSERT INTO person (
    full_name,
    birth_date,
    document,
    email,
    phone,
    created_at,
    updated_at
)
VALUES (
           'RHUAN SILVA RESENDE',
           DATE '1991-07-29',
           '03412808105',
           'rhuan.resende@hotmail.com.br',
           '62996487512',
           NOW(),
           NOW()
       );

INSERT INTO users (
    user_name,
    password,
    account_non_expired,
    account_non_locked,
    credentials_non_expired,
    enabled,
    person_id
)
VALUES (
           '03412808105',
           '{pbkdf2}7c7d035885d983c97ef22585a87d6cb3c4e500ddeaf9515e5bfd45398ca45d06c16213f61b85eb9a',
           true,
           true,
           true,
           true,
           1
       );

INSERT INTO user_permission (id_user, id_permission) VALUES
    (1, 1);
