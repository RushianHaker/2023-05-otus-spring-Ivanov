package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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

    @Autowired
    private TestEntityManager em;

    @Test
    void findById() {
        var dbAuthor = em.find(Author.class, 1);
        var author = authorDao.findById(1);

        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(dbAuthor.getId(), presentAuthor.getId());
        assertEquals(dbAuthor.getName(), presentAuthor.getName());
        assertEquals(dbAuthor.getYear(), presentAuthor.getYear());
    }

    @Test
    void findByNameAndYear() {
        var dbAuthor = em.find(Author.class, 1);
        var author = authorDao.findByNameAndYear("Andrey", 46);

        assertNotNull(author);
        assertEquals(dbAuthor.getId(), author.getId());
        assertEquals(dbAuthor.getName(), author.getName());
        assertEquals(dbAuthor.getYear(), author.getYear());
    }

    @Test
    void save() {
        var saveAuthor = authorDao.save(new Author("AAAAAA", 50L));
        var dbAuthor = em.find(Author.class, 3);

        assertEquals(dbAuthor.getId(), saveAuthor.getId());
        assertEquals(dbAuthor.getName(), saveAuthor.getName());
        assertEquals(dbAuthor.getYear(), saveAuthor.getYear());
    }

    @Test
    void updateById() {
        authorDao.updateById(new Author(1, "hello test", 11111));

        var dbAuthor = em.find(Author.class, 1);

        assertEquals(1, dbAuthor.getId());
        assertEquals("hello test", dbAuthor.getName());
        assertEquals(11111, dbAuthor.getYear());
    }

    @Test
    @Rollback
    void deleteById() {
        var dbAuthor = em.find(Author.class, 1);

        assertEquals(1, dbAuthor.getId());
        assertEquals("Andrey", dbAuthor.getName());
        assertEquals(46, dbAuthor.getYear());

        authorDao.deleteById(1);

        assertNull(em.find(Author.class, 1));
    }
}
