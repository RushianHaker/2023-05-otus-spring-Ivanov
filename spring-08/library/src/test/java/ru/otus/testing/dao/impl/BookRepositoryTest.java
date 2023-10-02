package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    void findById() {
        var book = bookRepository.findById("100");

        assertTrue(book.isPresent());

        var preasentBook = book.get();
        assertEquals("100", preasentBook.getId());
        assertEquals("Max", preasentBook.getName());
        assertEquals(333L, preasentBook.getYear());

        assertEquals("100", preasentBook.getGenre().getId());
        assertEquals("History", preasentBook.getGenre().getName());

        assertEquals("100", preasentBook.getAuthor().getId());
        assertEquals("Alex", preasentBook.getAuthor().getName());
        assertEquals(22L, preasentBook.getAuthor().getYear());

        assertEquals("100", preasentBook.getComments().get(0).getId());
        assertEquals("I like that book !", preasentBook.getComments().get(0).getCommentText());
    }

    @Test
    void findAll() {
        var book = bookRepository.findAll();

        assertEquals(2, book.size());

        var preasentBook = book.get(0);
        assertEquals("100", preasentBook.getId());
        assertEquals("Max", preasentBook.getName());
        assertEquals(333L, preasentBook.getYear());

        assertEquals("100", preasentBook.getGenre().getId());
        assertEquals("History", preasentBook.getGenre().getName());

        assertEquals("100", preasentBook.getAuthor().getId());
        assertEquals("Alex", preasentBook.getAuthor().getName());
        assertEquals(22L, preasentBook.getAuthor().getYear());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void save() {
        var author = new Author("aaa", 1111);
        var genre = new Genre("bbb");
        var comment = new Comment("ccc", new Book());
        var commentsList = Collections.singletonList(comment);
        var book = new Book("200", "war and peace", 4321L, author, genre, commentsList);

        var saveBook = bookRepository.save(book);
        var bookById = mongoOperations.findById("200", Book.class);

        assertNotNull(bookById);
        assertEquals(saveBook.getId(), bookById.getId());
        assertEquals(saveBook.getName(), bookById.getName());
        assertEquals(saveBook.getYear(), bookById.getYear());

        assertEquals(saveBook.getAuthor().getName(), bookById.getAuthor().getName());
        assertEquals(saveBook.getAuthor().getYear(), bookById.getAuthor().getYear());

        assertEquals(saveBook.getGenre().getName(), bookById.getGenre().getName());

        assertEquals(saveBook.getComments().get(0).getCommentText(), bookById.getComments().get(0).getCommentText());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteById() {
        var book = mongoOperations.findById("300", Book.class);

        assertNotNull(book);
        assertEquals("Andrey on tree", book.getName());
        assertEquals(555L, book.getYear());

        bookRepository.deleteById("300");

        assertNull(mongoOperations.findById("300", Book.class));
    }
}
