package ru.otus.testing.dao;

import ru.otus.testing.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Author save(Author author);

    Optional<Author> findById(long id);

    Author findByNameAndYear(Author author);

    void updateById(long id, Author author);

    void deleteById(long id);
}
