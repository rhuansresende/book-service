--liquibase formatted sql

--changeset rhuan.resende:v8-create-table-users-audit
CREATE TABLE IF NOT EXISTS public.users_AUD (
                             id bigint,
                             rev INT NOT NULL,
                             revtype SMALLINT,
                             account_non_expired boolean,
                             account_non_locked boolean,
                             credentials_non_expired boolean,
                             enabled BOOLEAN,
                             person_id bigint,
                             password VARCHAR(255),
                             user_name VARCHAR(100),
                             CONSTRAINT fk_usuario_aud_revision
                                 FOREIGN KEY (rev)
                                     REFERENCES customrevisionentity(id)
) TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users_AUD
    OWNER to postgres;