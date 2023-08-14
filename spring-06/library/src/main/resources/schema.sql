DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id        bigserial,
    book_name varchar(255),
    book_year bigint,
    primary key (id)
);

DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id           bigserial,
    authors_name varchar(255),
    author_year  bigint NOT NULL,
    book_id      bigint references books (id) on delete cascade,
    primary key (id)
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id          bigserial,
    genres_name varchar(255),
    book_id     bigint references books (id) on delete cascade,
    primary key (id)
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id           bigserial,
    comment_text varchar(1064),
    book_id      bigint references books (id) on delete cascade,
    primary key (id)
);