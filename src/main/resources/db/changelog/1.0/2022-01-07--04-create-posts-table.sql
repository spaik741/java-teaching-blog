--liquibase formatted sql

--changeset mihail:2022-01-07--04-create-posts-table
CREATE TABLE POSTS(ID BIGINT AUTO_INCREMENT PRIMARY KEY,
 TITLE VARCHAR (250),
 TEXT TEXT,
 DATE_POST DATE);