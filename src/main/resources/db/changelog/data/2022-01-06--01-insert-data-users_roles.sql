--liquibase formatted sql

--changeset mihail:2022-01-06--01-insert-data-users_roles
insert into users (username, password, email) values ('admin', 'password', 'admin@mail.ru'),
('misha','123', 'br.mihail@yandex.ru'),
('ivanov','abc', 'ivanov@google.com');
insert into roles(`name`) values ('ROLE_USER'), ('ROLE_ADMIN');
insert into users_roles (user_id, roles_id) values (1, 2), (2, 1), (3, 1);