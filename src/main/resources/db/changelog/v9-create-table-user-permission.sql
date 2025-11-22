--liquibase formatted sql

--changeset rhuan.resende:v5-create-table-user-permission
CREATE TABLE IF NOT EXISTS public.user_permission
(
    id_user bigint NOT NULL,
    id_permission bigint NOT NULL,
    CONSTRAINT fko47t1we6do84ku6rb9jcey2s9 FOREIGN KEY (id_permission)
    REFERENCES public.permission (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkprpp02ivhe66b5nrc0a3a4lk8 FOREIGN KEY (id_user)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_permission
    OWNER to postgres;