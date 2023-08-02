insert into genres (genres_name)
values ('horror');

insert into authors (authors_name, author_year)
values ('Martins', 32);

insert into comments (comment_text)
values ('I like that!');

insert into books (book_name, book_year, author_id, genre_id, comment_id)
values ('Dev Book', 2003, 1, 1, 1);
