package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Import({CommentDaoImpl.class})
@DataJpaTest
class CommentDaoImplTest {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private TestEntityManager em;

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
        var comment = commentDao.findByIdAndCommentText(List.of(new Comment(2, "Cool!", new Book())));

        assertEquals(1, comment.size());

        var presentComment = comment.get(0);
        assertEquals(2, presentComment.getId());
        assertEquals("Cool!", presentComment.getCommentText());
    }

    @Test
    void save() {
        commentDao.save(new Comment("hello test", new Book()));

        var comment = em.find(Comment.class, 3);

        assertEquals(3, comment.getId());
        assertEquals("hello test", comment.getCommentText());
    }

    @Test
    @Rollback
    void deleteById() {
        var comment = em.find(Comment.class, 1);

        assertEquals(1, comment.getId());
        assertEquals("I can write better!", comment.getCommentText());

        commentDao.deleteById(1);

        assertNull(em.find(Comment.class, 1));
    }
}
