insert into books (book_name, book_year)
values ('Test Book', 1852);

insert into genres (genres_name, book_id)
values ('comedy', 1);
insert into genres (genres_name, book_id)
values ('history', 1);

insert into authors (authors_name, author_year, book_id)
values ('Andrey', 46, 1);
insert into authors (authors_name, author_year, book_id)
values ('Tolstoy', 50, 1);

insert into comments (comment_text, book_id)
values ('I can write better!', 1);
insert into comments (comment_text, book_id)
values ('Cool!', 1);