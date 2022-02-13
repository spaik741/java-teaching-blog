--liquibase formatted sql

--changeset mihail:2022-01-06--01-insert-data-users_roles

INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES ('admin', 'password', 'admin@mail.ru'),
('misha','123', 'br.mihail@yandex.ru'),
('ivanov','abc', 'ivanov@google.com');
INSERT INTO ROLES(`NAME`) VALUES ('ROLE_USER'), ('ROLE_ADMIN');
INSERT INTO USERS_ROLES (USER_ID, ROLES_ID) VALUES (1, 2), (2, 1), (3, 1);