# Users schema

# --- !Ups

CREATE TABLE User (
    id UUID NOT NULL DEFAULT RANDOM_UUID(),
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(60) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE (name)
);

# --- !Downs
DROP TABLE IF EXISTS;