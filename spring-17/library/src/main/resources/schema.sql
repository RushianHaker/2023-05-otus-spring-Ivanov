DROP TABLE IF EXISTS authors CASCADE;
CREATE TABLE authors
(
    id           bigserial,
    authors_name varchar(255),
    author_year  bigint NOT NULL,
    primary key (id)
);

DROP TABLE IF EXISTS genres CASCADE;
CREATE TABLE genres
(
    id          bigserial,
    genres_name varchar(255),
    primary key (id)
);

DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books
(
    id        bigserial,
    book_name varchar(255),
    book_year bigint,
    author_id bigint references authors (id) on delete restrict,
    genre_id  bigint references genres (id) on delete restrict,
    primary key (id)
);

DROP TABLE IF EXISTS comments CASCADE;
CREATE TABLE comments
(
    id           bigserial,
    comment_text varchar(1064),
    book_id      bigint references books (id) on delete restrict,
    primary key (id)
);

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id bigserial,
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);