package ru.otus.testing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.testing.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
