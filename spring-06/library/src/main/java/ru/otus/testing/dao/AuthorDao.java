package ru.otus.testing.dao;

import ru.otus.testing.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Author save(Author author);

    Optional<Author> findById(long id);

    List<Author> findByNameAndYear(List<Author> authors);

    void updateById(long id, Author author);

    void deleteById(long id);
}
