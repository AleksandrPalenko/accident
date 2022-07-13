CREATE TABLE if not exists accident_type (
    id serial primary key,
    name varchar(2000)
    );

CREATE TABLE if not exists rules (
    id serial primary key,
    name varchar(2000)
    );

CREATE TABLE if not exists accident (
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    type_id int not null references accident_type(id)
    );

CREATE TABLE if not exists accident_rule (
    id serial primary key,
    accident_id int not null references accident(id),
    rule_id int not null references rules(id)
);

insert into rules (name) values ('Статья. 1');
insert into rules (name) values ('Статья. 2');
insert into rules (name) values ('Статья. 3');

insert into accident_type (name) values ('Две машины');
insert into accident_type (name) values ('Машина и человек');
insert into accident_type (name) values ('Машина и велосипед');

CREATE TABLE authorities (
    id serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
    id serial primary key,
    username VARCHAR(50) NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$4tgxeA6t3/mzum63x.11BODzpRTYbiN5Fg8fEzRNbrXSk56nN4ClS',
(select id from authorities where authority = 'ROLE_ADMIN'));