--liquibase formatted sql

--changeset mihail:2022-01-07--01-drop-tables
DROP TABLE IF EXISTS COMMENTS;
DROP TABLE IF EXISTS ROLES;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS POSTS;