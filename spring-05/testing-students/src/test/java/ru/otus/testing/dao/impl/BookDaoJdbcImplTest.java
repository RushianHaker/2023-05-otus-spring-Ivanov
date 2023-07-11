package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.BookDao;

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
        assertEquals(1, booksList.get(0).id());
        assertEquals("Test Book", booksList.get(0).name());
        assertEquals(1852, booksList.get(0).year());
        assertEquals("comedy", booksList.get(0).genre().name());
        assertEquals("Lera", booksList.get(0).author().name());
    }

    @Test
    void getById() {
        var book = bookDao.getById(1);

        assertNotNull(book);
        assertEquals(1, book.id());
        assertEquals("Test Book", book.name());
        assertEquals(1852, book.year());
        assertEquals("comedy", book.genre().name());
        assertEquals("Lera", book.author().name());
    }

    @Test
    void create() {
        //todo

       /* var book = bookDao.create(1);

        assertNotNull(book);
        assertEquals(1, book.id());
        assertEquals("Test Book", book.name());
        assertEquals(1852, book.year());
        assertEquals("Lera", book.genre().name());
        assertEquals("comedy", book.author().name());*/
    }

    @Test
    void update() {
       //todo

      /*  var book = bookDao.update(1);

        assertNotNull(book);
        assertEquals(1, book.id());
        assertEquals("Test Book", book.name());
        assertEquals(1852, book.year());
        assertEquals("Lera", book.genre().name());
        assertEquals("comedy", book.author().name());*/
    }

    @Test
    @Rollback
    void deleteById() {
        var book = bookDao.getAll();

        assertEquals(1, book.size());
        assertEquals("Test Book", book.get(0).name());

        bookDao.deleteById(1);

        assertEquals(0, bookDao.getAll().size());
    }
}
