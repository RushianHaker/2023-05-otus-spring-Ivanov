package ru.otus.testing.dao;

import ru.otus.testing.model.Book;

import java.util.List;
import java.util.Map;

public interface BookDao {
    Book create(Book book);

    Book getById(long id);

    Map<String, Long> getByIdAuthorAndGenreIds(long id);

    List<Book> getAll();

    void update(String name, Long year, long id);

    void deleteById(long id);
}
