insert into genres (genres_name)
values ('horror');

insert into authors (authors_name, author_year)
values ('Martins', 32);

insert into books (book_name, book_year, author_id, genre_id)
values ('Dev Book', 2003, 1, 1);

insert into comments (comment_text, book_id)
values ('I like that!', 1);

insert into users (username, password, role)
values ('admin', 'admin', 'ADMIN');

insert into users (username, password, role)
values ('user', 'user', 'USER');