package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;

import static org.junit.jupiter.api.Assertions.*;


//todo поправить методы
@Import({BookDaoImpl.class})
@JdbcTest
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

    //todo поправить методы
    @Test
    void create() {
        bookDao.save(new Book("war and peace", 4321L,
                new Author(2, "Tolstoy", 50L), new Genre(2, "history")));

        var book = bookDao.getById(2);

        assertNotNull(book);
        assertEquals(2, book.getId());
        assertEquals("war and peace", book.getName());
        assertEquals(4321, book.getYear());

        assertEquals(2, book.getGenre().getId());
        assertEquals("history", book.getGenre().getName());

        assertEquals(2, book.getAuthor().getId());
        assertEquals("Tolstoy", book.getAuthor().getName());
        assertEquals(50, book.getAuthor().getYear());

    }

    //todo поправить методы
    @Test
    void update() {
        bookDao.updateById("hello test", 1234L, 1, 1, 1);

        var book = bookDao.getById(1);

        assertNotNull(book);

        assertEquals(1, book.getId());
        assertEquals("hello test", book.getName());
        assertEquals(1234, book.getYear());

        assertEquals(1, book.getAuthor().getId());
        assertEquals(46, book.getAuthor().getYear());
        assertEquals("Andrey", book.getAuthor().getName());

        assertEquals(1, book.getGenre().getId());
        assertEquals("comedy", book.getGenre().getName());
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
