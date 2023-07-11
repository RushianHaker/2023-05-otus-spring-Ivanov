package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JdbcTest
class BookDaoJdbcImplTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void getAll() {
        var booksList = bookDao.getAll();

        assertNotNull(booksList);
        assertEquals(0, booksList.get(0).getId());
        assertEquals("Test Book", booksList.get(0).getName());
        assertEquals(1852, booksList.get(0).getYear());
        assertEquals("comedy", booksList.get(0).getGenre().getName());
        assertEquals("Lera", booksList.get(0).getAuthor().getName());
        assertEquals(56, booksList.get(0).getAuthor().getYear());
    }

    @Test
    void getById() {
        var book = bookDao.getById(0);

        assertNotNull(book);
        assertEquals(0, book.getId());
        assertEquals("Test Book", book.getName());
        assertEquals(1852, book.getYear());
        assertEquals("comedy", book.getGenre().getName());
        assertEquals("Lera", book.getAuthor().getName());
        assertEquals(56, book.getAuthor().getYear());
    }

    @Test
    void create() {
        bookDao.create(new Book(1, "war and peace", 4321L,
                new Author("Tolstoy", 50L), new Genre("history")));

        var book = bookDao.getById(1);

        assertNotNull(book);
        assertEquals(1, book.getId());
        assertEquals("war and peace", book.getName());
        assertEquals(4321, book.getYear());
        assertEquals("Tolstoy", book.getAuthor().getName());
        assertEquals(50, book.getAuthor().getYear());
        assertEquals("history", book.getGenre().getName());
    }

    @Test
    void update() {
        bookDao.update(new Book(2, "hello test", 1234L,
                new Author("Klara", 33L), new Genre("horror")), 0);

        var book = bookDao.getById(2);

        assertNotNull(book);
        assertEquals(2, book.getId());
        assertEquals("hello test", book.getName());
        assertEquals(1234, book.getYear());
        assertEquals("Klara", book.getAuthor().getName());
        assertEquals(33, book.getAuthor().getYear());
        assertEquals("horror", book.getGenre().getName());
    }

    @Test
    @Rollback
    void deleteById() {
        var book = bookDao.getAll();

        assertEquals(1, book.size());
        assertEquals("Test Book", book.get(0).getName());

        bookDao.deleteById(0);

        assertEquals(0, bookDao.getAll().size());
    }
}
