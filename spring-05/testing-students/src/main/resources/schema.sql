DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id   BIGINT PRIMARY KEY,
    "name" VARCHAR(255),
    "year" int4
);

DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id   BIGINT PRIMARY KEY,
    "name" VARCHAR(255),
    "year" int4
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id   BIGINT PRIMARY KEY,
    "name" VARCHAR(255)
);