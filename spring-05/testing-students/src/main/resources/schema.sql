DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id          BIGINT PRIMARY KEY,
    "name"      VARCHAR(255),
    "year"      int4,
    author      VARCHAR(255),
    genre       VARCHAR(255),
    author_year VARCHAR(255)
);

DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id     BIGINT PRIMARY KEY,
    "name" VARCHAR(255),
    "year" int4
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id     BIGINT PRIMARY KEY,
    "name" VARCHAR(255)
);