--liquibase formatted sql

--changeset mihail:2022-01-06--03-insert-data-comments
insert into comments (message, date_message, id_post, id_user) values ('Отличная статья, спасибо!' , '2021-10-06 ', 1, 1),
('Машинный перевод. Смысл очень сложно уловить, хоть отредактировали бы, перед выкладыванием' , '2022-01-01', 2, 1),
('Если честно, странное решение.
Если из всего ответа нужно только два поля, логично было получить только их.
Судя по коду зачем-то была повторена внутренняя структура ответа, для чего и понадобился вложенный класс.' , '2021-12-23', 1, 2);