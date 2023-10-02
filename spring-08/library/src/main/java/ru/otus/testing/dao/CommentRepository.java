package ru.otus.testing.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.testing.model.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
