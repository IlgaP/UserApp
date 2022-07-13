--liquibase formatted sql

--changeset ilga:1



CREATE TABLE app_user
(
    id VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    app_user_role VARCHAR(255) NOT NULL
);

INSERT INTO app_user (id, email, password, app_user_role)
VALUES (1, 'user', '$2a$10$Mne9oMSwpvlwhUzKZ5/wJe/EAi7M3CXPQa9AlcIX7FH7oAO2EMYvu', 'USER');

CREATE TABLE auth_token
(
    id BIGINT PRIMARY KEY NOT NULL UNIQUE,
    token VARCHAR(255) NOT NULL,
    app_user_id VARCHAR(255) NOT NULL,
    CONSTRAINT auth_token_app_user_id_fkey FOREIGN KEY (app_user_id) REFERENCES app_user (id)
);

CREATE SEQUENCE id_sequence START WITH 2 INCREMENT BY 1;

CREATE TABLE avatar
(
    id VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE,
    file BLOB NOT NULL,
    app_user_id VARCHAR(255) NOT NULL,
    CONSTRAINT avatar_app_user_id_fkey FOREIGN KEY (app_user_id) REFERENCES app_user (id)


);