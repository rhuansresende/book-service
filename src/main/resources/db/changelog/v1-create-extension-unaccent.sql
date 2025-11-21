--liquibase formatted sql

--changeset rhuan.resende:v1-create-extension-unaccent
CREATE EXTENSION IF NOT EXISTS unaccent;