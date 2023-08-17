package ru.otus.testing.dao;

import ru.otus.testing.model.Author;

import java.util.Optional;

public interface AuthorDao {
    Author save(Author author);

    Optional<Author> findById(long id);

    Author findByNameAndYear(String name, long year);

    void updateById(Author author);

    void deleteById(long id);
}
