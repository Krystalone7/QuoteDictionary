DROP TABLE IF EXISTS quotes;

create table quotes
(
    id bigserial primary key not null,
    text varchar(255) not null,
    author varchar(255) not null
);