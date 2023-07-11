package ru.otus.testing.dao;

import ru.otus.testing.model.Genre;

import java.util.List;

public interface GenreDao {
    void create(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void update(Genre genre, long id);

    void deleteById(long id);
}
