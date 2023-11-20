package ru.otus.testing.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.testing.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
