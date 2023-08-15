package ru.otus.testing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.testing.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select s from Comment s where s.commentText in ( :comments )")
    List<Comment> findByCommentText(@Param("comments") List<String> comments);
}
