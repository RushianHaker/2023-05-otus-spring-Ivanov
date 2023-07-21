package ru.otus.testing.dao;

import ru.otus.testing.model.Genre;

import java.util.List;

public interface GenreDao {
    Genre create(Genre genre);

    Genre getById(long id);

    Genre getByName(String name);

    List<Genre> getAll();

    void update(Genre genre, long id);

    void deleteById(long id);
}
