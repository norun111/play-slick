# Users schema

# --- !Ups

CREATE TABLE User (
    userId UUID NOT NULL DEFAULT RANDOM_UUID(),
    firstName varchar(255) NOT NULL,
    lastName varchar(255) NOT NULL,
    fullName varchar(255),
    email varchar(255) NOT NULL,
    password varchar(60) NOT NULL,
    avatarURL varchar(60),
    activated bit,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE (email)
);

# --- !Downs
DROP TABLE IF EXISTS;