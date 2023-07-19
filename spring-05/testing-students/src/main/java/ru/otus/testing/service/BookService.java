package ru.otus.testing.service;


import ru.otus.testing.model.Book;

import java.util.List;

public interface BookService {
    Book create(String bookName, long bookYear, String authorName, long authorYear, String genreName);

    Book readById();

    List<Book> readAll();

    void update(long bookId, String bookName, long bookYear, String authorName, long authorYear, String genreName);

    void delete();
}
