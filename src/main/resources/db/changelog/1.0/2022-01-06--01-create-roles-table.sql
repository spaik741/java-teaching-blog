--liquibase formatted sql

--changeset mihail:2022-01-06--01-create-roles-table
CREATE TABLE ROLES(ID BIGINT AUTO_INCREMENT PRIMARY KEY,
 NAME VARCHAR(30));