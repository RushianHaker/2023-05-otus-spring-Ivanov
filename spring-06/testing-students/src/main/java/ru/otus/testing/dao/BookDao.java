package ru.otus.testing.dao;

import ru.otus.testing.model.Book;

import java.util.List;

public interface BookDao {
    Book create(Book book);

    Book getById(long id);

    List<Book> getAll();

    void update(String name, Long year, long authorId, long genreId, long id);

    void deleteById(long id);
}
