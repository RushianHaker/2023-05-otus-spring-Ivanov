package ru.otus.testing.dao;

import ru.otus.testing.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findByIdAndCommentText(List<Comment> comments);

    void updateById(Comment comment);

    void deleteById(long id);
}
