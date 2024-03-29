package ru.otus.testing.dao;

import ru.otus.testing.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void updateById(Book book);

    void deleteById(long id);
}
