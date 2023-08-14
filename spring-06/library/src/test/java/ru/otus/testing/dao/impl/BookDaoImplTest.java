package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


//todo поправить методы
@Import({BookDaoImpl.class})
@DataJpaTest
class BookDaoImplTest {
    @Autowired
    private BookDao bookDao;

    @Test
    void findById() {
        var book = bookDao.findById(1);

        assertTrue(book.isPresent());

        var preasentBook = book.get();
        assertEquals(1, preasentBook.getId());
        assertEquals("Test Book", preasentBook.getName());
        assertEquals(1852, preasentBook.getYear());

        assertEquals(1, preasentBook.getGenre().get(0).getId());
        assertEquals("comedy", preasentBook.getGenre().get(0).getName());

        assertEquals(1, preasentBook.getAuthor().get(0).getId());
        assertEquals("Andrey", preasentBook.getAuthor().get(0).getName());
        assertEquals(46, preasentBook.getAuthor().get(0).getYear());

        assertEquals(46, preasentBook.getComment().get(0).getId());
        assertEquals("cool book", preasentBook.getComment().get(0).getCommentText());
    }

    @Test
    void findAll() {
        var book = bookDao.findAll();

        assertEquals(1, book.size());

        var preasentBook = book.get(0);
        assertEquals(1, preasentBook.getId());
        assertEquals("Test Book", preasentBook.getName());
        assertEquals(1852, preasentBook.getYear());

        assertEquals(1, preasentBook.getGenre().get(0).getId());
        assertEquals("comedy", preasentBook.getGenre().get(0).getName());

        assertEquals(1, preasentBook.getAuthor().get(0).getId());
        assertEquals("Andrey", preasentBook.getAuthor().get(0).getName());
        assertEquals(46, preasentBook.getAuthor().get(0).getYear());

        assertEquals(46, preasentBook.getComment().get(0).getId());
        assertEquals("cool book", preasentBook.getComment().get(0).getCommentText());
    }

    @Test
    void create() {
        bookDao.save(new Book("war and peace", 4321L,
                List.of(new Author("Tolstoy", 1111)), List.of(new Genre("history")),
                List.of(new Comment("history"))));

        var book = bookDao.findById(2);
        assertTrue(book.isPresent());

        var presentBook = book.get();
        assertEquals(2, presentBook.getId());
        assertEquals("war and peace", presentBook.getName());
        assertEquals(4321, presentBook.getYear());

        assertEquals(2, presentBook.getGenre().get(0).getId());
        assertEquals("history", presentBook.getGenre().get(0).getName());

        assertEquals(2, presentBook.getAuthor().get(0).getId());
        assertEquals("Tolstoy", presentBook.getAuthor().get(0).getName());
        assertEquals(1111, presentBook.getAuthor().get(0).getYear());

        assertEquals("Tolstoy", presentBook.getComment().get(0).getCommentText());
    }

    @Test
    void update() {
        bookDao.updateById(1, new Book("Tolstoy Tolstoy Tolstoy", 1111L,
                List.of(new Author("AAAAA", 1111)), List.of(new Genre("AAAAA")),
                List.of(new Comment("AAAAA"))));

        var book = bookDao.findById(1);
        assertTrue(book.isPresent());

        var presentBook = book.get();
        assertEquals(2, presentBook.getId());
        assertEquals("Tolstoy Tolstoy Tolstoy", presentBook.getName());
        assertEquals(4321, presentBook.getYear());

        assertEquals(1, presentBook.getGenre().get(0).getId());
        assertEquals("AAAAA", presentBook.getGenre().get(0).getName());

        assertEquals(1, presentBook.getAuthor().get(0).getId());
        assertEquals("AAAAA", presentBook.getAuthor().get(0).getName());
        assertEquals(1111, presentBook.getAuthor().get(0).getYear());

        assertEquals(1, presentBook.getComment().get(0).getId());
        assertEquals("AAAAA", presentBook.getComment().get(0).getCommentText());
    }

    @Test
    @Rollback
    void deleteById() {
        var book = bookDao.findAll();
        assertEquals(1, book.size());
        assertEquals(1, book.get(0).getId());
        assertEquals("Test Book", book.get(0).getName());

        bookDao.deleteById(1);

        assertEquals(0, bookDao.findAll().size());
    }
}
