package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

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
    @Rollback
    void deleteById() {
        var comment = em.find(Comment.class, 1);

        assertEquals(1, comment.getId());
        assertEquals("I can write better!", comment.getCommentText());

        commentRepository.deleteById(1L);

        assertNull(em.find(Comment.class, 1));
    }
}
