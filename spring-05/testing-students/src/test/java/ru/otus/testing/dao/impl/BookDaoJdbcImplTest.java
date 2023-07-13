package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
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
    @Autowired
    private JdbcTemplate template;

    @Test
    void getAll() {
        var booksList = bookDao.getAll();

        assertNotNull(booksList);

        assertEquals(1, booksList.get(0).getId());
        assertEquals("Test Book", booksList.get(0).getName());
        assertEquals(1852, booksList.get(0).getYear());

        assertEquals(1, booksList.get(0).getGenre().getId());
        assertEquals("comedy", booksList.get(0).getGenre().getName());

        assertEquals(1, booksList.get(0).getAuthor().getId());
        assertEquals("Andrey", booksList.get(0).getAuthor().getName());
        assertEquals(46, booksList.get(0).getAuthor().getYear());
    }

    @Test
    void getById() {
        var book = bookDao.getById(1);

        assertNotNull(book);

        assertEquals(1, book.getId());
        assertEquals("Test Book", book.getName());
        assertEquals(1852, book.getYear());

        assertEquals(1, book.getGenre().getId());
        assertEquals("comedy", book.getGenre().getName());

        assertEquals(1, book.getAuthor().getId());
        assertEquals("Andrey", book.getAuthor().getName());
        assertEquals(46, book.getAuthor().getYear());
    }

    @Test
    void create() {
        template.update("insert into authors (authors_name, author_year) values ('Tolstoy', 50);");
        template.update("insert into genres (genres_name) values ('history');");

        bookDao.create(new Book("war and peace", 4321L,
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

    @Test
    void update() {
        bookDao.update("hello test", 1234L, 1);

        var book = bookDao.getById(1);

        assertNotNull(book);

        assertEquals(1, book.getId());
        assertEquals("hello test", book.getName());
        assertEquals(1234L, book.getYear());
    }

    @Test
    @Rollback
    void deleteById() {
        var book = bookDao.getAll();

        assertEquals(1, book.size());
        assertEquals("Test Book", book.get(0).getName());

        bookDao.deleteById(1);

        assertEquals(0, bookDao.getAll().size());
    }
}
