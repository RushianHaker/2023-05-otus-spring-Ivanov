package ru.otus.testing.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.testing.model.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
