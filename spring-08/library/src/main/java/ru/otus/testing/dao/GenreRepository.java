package ru.otus.testing.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByName(String name);
}
