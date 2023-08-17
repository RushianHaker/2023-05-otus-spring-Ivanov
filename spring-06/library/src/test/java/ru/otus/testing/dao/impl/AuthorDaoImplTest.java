package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.model.Author;

import static org.junit.jupiter.api.Assertions.*;

@Import({AuthorDaoImpl.class})
@DataJpaTest
class AuthorDaoImplTest {
    @Autowired
    private AuthorDao authorDao;

    @Test
    void findById() {
        var author = authorDao.findById(1);

        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(1, presentAuthor.getId());
        assertEquals("Andrey", presentAuthor.getName());
        assertEquals(46, presentAuthor.getYear());
    }

    @Test
    void findByNameAndYear() {
        var author = authorDao.findByNameAndYear(new Author("Andrey", 46));

        assertNotNull(author);
        assertEquals(1, author.getId());
        assertEquals("Andrey", author.getName());
        assertEquals(46, author.getYear());
    }

    @Test
    void save() {
        authorDao.save(new Author("AAAAAA", 50L));

        var author = authorDao.findById(3);
        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(3, presentAuthor.getId());
        assertEquals("AAAAAA", presentAuthor.getName());
        assertEquals(50L, presentAuthor.getYear());
    }

    @Test
    void updateById() {
        authorDao.updateById(new Author(1, "hello test", 11111));

        var author = authorDao.findById(1);
        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(1, presentAuthor.getId());
        assertEquals("hello test", presentAuthor.getName());
        assertEquals(11111, presentAuthor.getYear());
    }

    @Test
    @Rollback
    void deleteById() {
        var author = authorDao.findById(1);
        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(1, presentAuthor.getId());
        assertEquals("Andrey", presentAuthor.getName());
        assertEquals(46, presentAuthor.getYear());

        authorDao.deleteById(1);

        assertTrue(authorDao.findById(1).isEmpty());
    }
}
