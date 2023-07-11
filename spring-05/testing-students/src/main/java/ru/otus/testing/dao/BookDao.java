package ru.otus.testing.dao;

import ru.otus.testing.model.Book;

import java.util.List;

public interface BookDao {
    void create(Book book);

    Book getById(long id);

    List<Book> getAll();

    void update(Book book, long id);

    void deleteById(long id);
}
