--liquibase formatted sql

--changeset mihail:2022-01-06--02-create-users-table
CREATE TABLE USERS(ID SERIAL UNIQUE ,
USERNAME VARCHAR(15),
PASSWORD VARCHAR(30),
EMAIL VARCHAR(100));