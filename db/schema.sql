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

CREATE TABLE if not exists users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    PRIMARY KEY (username)
);

CREATE TABLE if not exists authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);