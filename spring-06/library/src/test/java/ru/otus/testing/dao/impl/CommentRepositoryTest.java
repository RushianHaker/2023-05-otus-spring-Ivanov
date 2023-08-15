package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.model.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findById() {
        var comment = commentRepository.findById(1L);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(1, presentComment.getId());
        assertEquals("I can write better!", presentComment.getCommentText());
    }

    @Test
    void findByCommentsTextList() {
        var comment = commentRepository.findByCommentsTextList(List.of("I can write better!", "Cool!"));

        assertEquals(2, comment.size());

        var presentFirstComment = comment.get(0);
        assertEquals(1, presentFirstComment.getId());
        assertEquals("I can write better!", presentFirstComment.getCommentText());

        var presentSecondComment = comment.get(1);
        assertEquals(2, presentSecondComment.getId());
        assertEquals("Cool!", presentSecondComment.getCommentText());
    }

    @Test
    void save() {
        commentRepository.save(new Comment("hello test"));

        var comment = commentRepository.findById(2L);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(2, presentComment.getId());
        assertEquals("Cool!", presentComment.getCommentText());
    }

    @Test
    @Rollback
    void deleteById() {
        var comment = commentRepository.findById(1L);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(1, presentComment.getId());
        assertEquals("I can write better!", presentComment.getCommentText());

        commentRepository.deleteById(1L);

        assertTrue(commentRepository.findById(1L).isEmpty());
    }
}
