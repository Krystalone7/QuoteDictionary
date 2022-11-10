drop table if exists user_role;
drop table if exists roles;
drop table if exists users;

create table users
(
    id        bigserial primary key,
    username  varchar(25) not null,
    email     varchar(100) not null,
    password  varchar(100) not null
);

create table roles
(
    id bigserial primary key,
    name varchar(25) not null
);

create table user_role
(
    id serial primary key,
    user_id bigserial not null
        references users(id),
    role_id bigserial not null
        references roles(id)
);
insert into roles
values
    (1, 'admin'),
    (2, 'user');