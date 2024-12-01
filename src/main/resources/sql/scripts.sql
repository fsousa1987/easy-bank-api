create table users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);
create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

INSERT IGNORE INTO `users`
VALUES ('user', '{noop}Francisco@12345', '1');
INSERT IGNORE INTO `authorities`
VALUES ('user', 'read');

INSERT IGNORE INTO `users`
VALUES ('admin', '{bcrypt}$2a$12$IwCVDkCnVDme9z8/Tdfy.umuYkk/ywS8FIZwYP1Ksq8OHEvI5QoVW', '1');
INSERT IGNORE INTO `authorities`
VALUES ('admin', 'admin');