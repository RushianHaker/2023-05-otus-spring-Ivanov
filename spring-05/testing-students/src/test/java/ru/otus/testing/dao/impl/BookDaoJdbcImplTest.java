package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.testing.dao.BookDao;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
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
        assertEquals("Lera", booksList.get(0).genre().name());
        assertEquals("comedy", booksList.get(0).author().name());
    }

    @Test
    void getById() {
        var book = bookDao.getById(1);

        assertNotNull(book);
        assertEquals(1, book.id());
        assertEquals("Test Book", book.name());
        assertEquals(1852, book.year());
        assertEquals("Lera", book.genre().name());
        assertEquals("comedy", book.author().name());
    }

    //todo
    @Test
    void create() {
        var book = bookDao.create(1);

        assertNotNull(book);
        assertEquals(1, book.id());
        assertEquals("Test Book", book.name());
        assertEquals(1852, book.year());
        assertEquals("Lera", book.genre().name());
        assertEquals("comedy", book.author().name());
    }

    //todo
    @Test
    void update() {
        var book = bookDao.update(1);

        assertNotNull(book);
        assertEquals(1, book.id());
        assertEquals("Test Book", book.name());
        assertEquals(1852, book.year());
        assertEquals("Lera", book.genre().name());
        assertEquals("comedy", book.author().name());
    }

    @Test
    void deleteById() {
        var book = bookDao.getById(1);

        assertEquals(1, book.id());
        assertEquals("Test Book", book.name());

        bookDao.deleteById(1);

        var bookAfterDel = bookDao.getById(1);

        assertNull(bookAfterDel);
    }
}
