package ru.otus.testing.service;


import ru.otus.testing.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
