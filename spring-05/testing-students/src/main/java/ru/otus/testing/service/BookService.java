package ru.otus.testing.service;


import ru.otus.testing.model.Book;

import java.util.List;

public interface BookService {
    Book create();

    Book readById();

    List<Book> readAll();

    void update();

    void delete();
}
