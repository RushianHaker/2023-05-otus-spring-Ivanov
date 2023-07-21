package ru.otus.testing.dao;

import ru.otus.testing.model.Author;

import java.util.List;

public interface AuthorDao {
    Author create(Author author);

    Author getById(long id);

    Author getByName(String name);

    List<Author> getAll();

    void update(Author author, long id);

    void deleteById(long id);
}
