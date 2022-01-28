DROP TABLE IF EXISTS USERS_ROLES;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ROLES;
DROP TABLE IF EXISTS POSTS;
DROP TABLE IF EXISTS COMMENTS;

CREATE TABLE ROLES(ID BIGINT AUTO_INCREMENT PRIMARY KEY,
 NAME VARCHAR(30));
CREATE TABLE USERS(ID BIGINT AUTO_INCREMENT PRIMARY KEY,
USERNAME VARCHAR(15),
PASSWORD VARCHAR(30),
EMAIL VARCHAR(100));
CREATE TABLE USERS_ROLES(USER_ID BIGINT not null,
    ROLES_ID BIGINT not null,
        foreign key (roles_id) references ROLES(ID),
        foreign key (user_id) references USERS(ID));
CREATE TABLE POSTS(ID BIGINT AUTO_INCREMENT PRIMARY KEY,
 TITLE VARCHAR (250),
 TEXT TEXT,
 DATE_POST DATE);
CREATE TABLE COMMENTS(ID BIGINT AUTO_INCREMENT PRIMARY KEY,
MESSAGE VARCHAR(250),
DATE_MESSAGE TIMESTAMP,
ID_USER BIGINT,
ID_POST BIGINT,
FOREIGN KEY (ID_USER) REFERENCES USERS(ID),
FOREIGN KEY (ID_POST) REFERENCES POSTS(ID) ON DELETE CASCADE);

insert into users (username, password, email) values ('admin', 'password', 'admin@mail.ru'),
('misha','123', 'br.mihail@yandex.ru'),
('ivanov','abc', 'ivanov@google.com');
insert into roles(`name`) values ('ROLE_USER'), ('ROLE_ADMIN');
insert into users_roles (user_id, roles_id) values (1, 2), (2, 1), (3, 1);

insert into posts (title, text, date_post) values ('Java в 2021 году: обновления 16 и 17, популярность в Азии и стабильность в рейтингах',
'Раз в полгода язык Java получает обновление. Ожидаемо, в 2021 году вышли два релиза — Java 16 и Java 17.В релиз Java 16 вошло 17 улучшений. Среди новшеств 16-й версии: новый инструмент...',
'2021-10-05'),
('Метод Constructor в Java I Перегрузка Constructor. Абстрактные классы и интерфейсы Java', 'Как и другие методы, мы также можем определить метод Constructor в нашей Java-программе, но, в отличие от других методов, мы не можем вызвать Constructor напрямую; Java вызывает конструктор автоматически при создании объекта. Когда мы используем ключевое слово new для создания объекта класса, Java делает три вещи:',
'2021-12-23'),
('Мой первый опыт работы с Jackson-ом.', 'Всем привет!Как-то раз в далекой-далекой галактике нашелся очень длинный JSON...',
'2022-01-01');

insert into comments (message, date_message, id_post, id_user) values ('Отличная статья, спасибо!' , '2021-10-06 ', 1, 1),
('Машинный перевод. Смысл очень сложно уловить, хоть отредактировали бы, перед выкладыванием' , '2022-01-01', 2, 1),
('Если честно, странное решение.
Если из всего ответа нужно только два поля, логично было получить только их.
Судя по коду зачем-то была повторена внутренняя структура ответа, для чего и понадобился вложенный класс.' , '2021-12-23', 1, 2);