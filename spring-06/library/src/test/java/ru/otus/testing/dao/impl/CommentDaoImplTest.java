package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.model.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Import({CommentDaoImpl.class})
@JdbcTest
class CommentDaoImplTest {
    @Autowired
    private CommentDao commentDao;

    @Test
    void findById() {
        var comment = commentDao.findById(1);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(1, presentComment.getId());
        assertEquals("Test Book", presentComment.getCommentText());
    }

    @Test
    void findByNameAndId() {
        var comment = commentDao.findByNameAndId(List.of(new Comment(1, "hello world")));

        assertEquals(1, comment.size());

        var presentComment = comment.get(0);
        assertEquals(1, presentComment.getId());
        assertEquals("Test Book", presentComment.getCommentText());
    }

    @Test
    void save() {
        commentDao.save(new Comment(2, "history"));

        var comment = commentDao.findById(1);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(1, presentComment.getId());
        assertEquals("Test Book", presentComment.getCommentText());
    }

    @Test
    void updateById() {
        commentDao.updateById(1, new Comment(1234L, "hello test"));

        var comment = commentDao.findById(1);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(1, presentComment.getId());
        assertEquals("Test Book", presentComment.getCommentText());
    }

    @Test
    @Rollback
    void deleteById() {
        var comment = commentDao.findById(1);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(1, presentComment.getId());
        assertEquals("Test Book", presentComment.getCommentText());

        commentDao.deleteById(1);

        assertTrue(commentDao.findById(1).isEmpty());
    }
}
