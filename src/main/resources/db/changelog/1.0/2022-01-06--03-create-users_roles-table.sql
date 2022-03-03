--liquibase formatted sql

--changeset mihail:2022-01-06--03-create-users_roles-table
CREATE TABLE USERS_ROLES(USER_ID BIGINT not null,
    ROLES_ID BIGINT not null,
        foreign key (roles_id) references ROLES(ID),
        foreign key (user_id) references USERS(ID));