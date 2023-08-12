package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.model.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import({AuthorDaoImpl.class})
@JdbcTest
class AuthorDaoImplTest {
    @Autowired
    private AuthorDao authorDao;

    @Test
    void findById() {
        var author = authorDao.findById(1);

        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(1, presentAuthor.getId());
        assertEquals("Test Book", presentAuthor.getName());
        assertEquals(1852, presentAuthor.getYear());
    }

    @Test
    void findByNameAndYear() {
        var author = authorDao.findByNameAndYear(List.of(new Author("Test Book", 1)));

        assertEquals(1, author.size());

        var presentAuthor = author.get(0);
        assertEquals(1, presentAuthor.getId());
        assertEquals("Test Book", presentAuthor.getName());
        assertEquals(1852, presentAuthor.getYear());
    }

    @Test
    void save() {
        authorDao.save(new Author(2, "Tolstoy", 50L));

        var author = authorDao.findById(2);
        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(1, presentAuthor.getId());
        assertEquals("Test Book", presentAuthor.getName());
        assertEquals(1852, presentAuthor.getYear());
    }

    @Test
    void updateById() {
        authorDao.updateById(1, new Author("hello test", 11111));

        var author = authorDao.findById(2);
        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(1, presentAuthor.getId());
        assertEquals("Test Book", presentAuthor.getName());
        assertEquals(1852, presentAuthor.getYear());
    }

    @Test
    @Rollback
    void deleteById() {
        var author = authorDao.findById(1);
        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(1, presentAuthor.getId());
        assertEquals("Test Book", presentAuthor.getName());
        assertEquals(1852, presentAuthor.getYear());

        authorDao.deleteById(1);

        assertTrue(authorDao.findById(1).isEmpty());
    }
}
