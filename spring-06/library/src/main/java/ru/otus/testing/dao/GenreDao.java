package ru.otus.testing.dao;

import ru.otus.testing.model.Genre;

import java.util.Optional;

public interface GenreDao {
    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    Genre findByName(Genre genre);

    void updateById(long id, Genre genre);

    void deleteById(long id);
}
