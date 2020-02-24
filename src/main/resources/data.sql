--drop INDEX FK74aoh99stptslhotgf41fitt0;
drop table if exists roles cascade;
--drop INDEX UK_mufchskagt7e1w4ksmt9lum5l;
drop table if exists user cascade;

CREATE TABLE roles
(
    id     INT NOT NULL AUTO_INCREMENT,
    level INT,
    typ   VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       INT          NOT NULL AUTO_INCREMENT,
    firstname     VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    role     INT          NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX UK_mufchskagt7e1w4ksmt9lum5l ON user (username ASC);

CREATE INDEX FK74aoh99stptslhotgf41fitt0 ON user (role ASC);

insert into roles (level, typ) values(0, 'admin');
insert into roles (level, typ) values(1, 'user');

insert into user (firstname, lastname, password, username, role) values('admin', 'admin', 'admin', 'admin', 1);
insert into user (firstname, lastname, password, username, role) values('John', 'Doe', 'superSecret', 'jdoe', 2);