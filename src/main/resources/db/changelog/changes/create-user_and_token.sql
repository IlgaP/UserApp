--liquibase formatted sql

--changeset ilga:1

CREATE TABLE app_user
(
    id VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE auth_token
(
    id BIGINT PRIMARY KEY NOT NULL UNIQUE,
    token VARCHAR(255) NOT NULL,
    app_user_id VARCHAR(255) NOT NULL,
    CONSTRAINT auth_token_app_user_id_fkey FOREIGN KEY (app_user_id) REFERENCES app_user (id)
);

CREATE SEQUENCE id_sequence START WITH 1 INCREMENT BY 1;