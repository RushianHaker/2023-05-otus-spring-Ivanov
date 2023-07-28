package ru.otus.testing.dao;

import ru.otus.testing.model.Author;

import java.util.Optional;

public interface AuthorDao {
    Author save(Author author);

    Optional<Author> findById(long id);

    Optional<Author> findByName(String name);

    void updateById(long id, Author author);

    void deleteById(long id);
}
