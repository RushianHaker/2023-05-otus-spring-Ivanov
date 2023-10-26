package ru.otus.testing.service;


import ru.otus.testing.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
