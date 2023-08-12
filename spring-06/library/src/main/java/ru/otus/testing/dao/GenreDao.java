package ru.otus.testing.dao;

import ru.otus.testing.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    List<Genre> findByNameAndId(List<Genre> genres);

    void updateById(long id, Genre genre);

    void deleteById(long id);
}
