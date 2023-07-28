package ru.otus.testing.dao;

import ru.otus.testing.model.Comment;

import java.util.Optional;

public interface CommentDao {
    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    Optional<Comment> findByName(String name);

    void updateById(long id, Comment comment);

    void deleteById(long id);
}
