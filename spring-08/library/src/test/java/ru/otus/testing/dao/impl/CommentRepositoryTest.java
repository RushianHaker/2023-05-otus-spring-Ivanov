package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    void findById() {
        var comment = commentRepository.findById("100");

        assertTrue(comment.isPresent());

        var presentComment = comment.get();
        assertEquals("100", presentComment.getId());
        assertEquals("I like that book !", presentComment.getCommentText());

        assertEquals("100", presentComment.getBook().getId());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteById() {
        var comment = mongoOperations.findById("300",Comment.class);
        assertNotNull(comment);

        assertEquals("300", comment.getId());
        assertEquals("I don't love this author !", comment.getCommentText());

        commentRepository.deleteById("300");

        assertNull(mongoOperations.findById("300",Comment.class));
    }
}
