package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.dao.impl.mapper.BookMapper;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;

import static org.junit.jupiter.api.Assertions.assertNotNull;


//todo поправить методы
@Import({BookDaoJdbcImpl.class, BookMapper.class})
@JdbcTest
class CommentDaoJdbcImplTest {
    @Autowired
    private BookDao bookDao;

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
        bookDao.update("hello test", 1234L, 1, 1, 1);

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
        var book = bookDao.getAll();

        assertEquals(1, book.size());
        assertEquals("Test Book", book.get(0).getName());

        bookDao.deleteById(1);

        assertEquals(0, bookDao.getAll().size());
    }
}
