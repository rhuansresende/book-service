--liquibase formatted sql

--changeset rhuan.resende:v2-create-table-book
CREATE TABLE book (
                      id BIGSERIAL PRIMARY KEY,
                      create_at TIMESTAMP,
                      name TEXT NOT NULL,
                      status SMALLINT CHECK (status >= 0 AND status <= 1)
);
