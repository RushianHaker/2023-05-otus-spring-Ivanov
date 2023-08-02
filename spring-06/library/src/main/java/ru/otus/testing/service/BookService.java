package ru.otus.testing.service;


import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

import java.util.List;

public interface BookService {
    Book create(String bookName, long bookYear, List<Author> authorsList, List<Genre> genresList,
                List<Comment> commentsList);

    Book readById(long bookId);

    List<Book> readAll();

    void update(long bookId, String bookName, long bookYear, List<Author> authorsList, List<Genre> genresList,
                List<Comment> commentsList);

    void delete(long bookId);
}
