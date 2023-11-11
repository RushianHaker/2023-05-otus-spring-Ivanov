insert into genres (genres_name)
values ('comedy');
insert into genres (genres_name)
values ('history');

insert into authors (authors_name, author_year)
values ('Andrey', 46);
insert into authors (authors_name, author_year)
values ('Tolstoy', 50);

insert into books (book_name, book_year, author_id, genre_id)
values ('Test Book', 1852, 1, 1);

insert into comments (comment_text, book_id)
values ('I can write better!', 1);
insert into comments (comment_text, book_id)
values ('Cool!', 1);