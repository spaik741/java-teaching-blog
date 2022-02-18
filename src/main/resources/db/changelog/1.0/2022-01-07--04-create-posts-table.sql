--liquibase formatted sql

--changeset mihail:2022-01-07--04-create-posts-table
CREATE TABLE POSTS(ID SERIAL UNIQUE ,
 TITLE VARCHAR (250),
 TEXT TEXT,
 DATE_POST DATE);