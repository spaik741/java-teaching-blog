--liquibase formatted sql

--changeset mihail:2022-01-06--01-create-roles-table
CREATE TABLE ROLES(ID SERIAL UNIQUE,
 NAME VARCHAR(30));