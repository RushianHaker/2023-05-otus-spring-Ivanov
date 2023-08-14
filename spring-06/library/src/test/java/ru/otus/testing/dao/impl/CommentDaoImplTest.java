package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.model.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Import({CommentDaoImpl.class})
@DataJpaTest
class CommentDaoImplTest {
    @Autowired
    private CommentDao commentDao;

    @Test
    void findById() {
        var comment = commentDao.findById(1);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(1, presentComment.getId());
        assertEquals("I can write better!", presentComment.getCommentText());
    }

    @Test
    void findByIdAndCommentText() {
        var comment = commentDao.findByIdAndCommentText(List.of(new Comment(2, "Cool!")));

        assertEquals(1, comment.size());

        var presentComment = comment.get(0);
        assertEquals(2, presentComment.getId());
        assertEquals("Cool!", presentComment.getCommentText());
    }

    @Test
    void save() {
        commentDao.save(new Comment("hello test"));

        var comment = commentDao.findById(3);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(3, presentComment.getId());
        assertEquals("hello test", presentComment.getCommentText());
    }

    @Test
    void updateById() {
        commentDao.updateById(1, new Comment("hello test"));

        var comment = commentDao.findById(1);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(1, presentComment.getId());
        assertEquals("hello test", presentComment.getCommentText());
    }

    @Test
    @Rollback
    void deleteById() {
        var comment = commentDao.findById(1);

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals(1, presentComment.getId());
        assertEquals("I can write better!", presentComment.getCommentText());

        commentDao.deleteById(1);

        assertTrue(commentDao.findById(1).isEmpty());
    }
}
