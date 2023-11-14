package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.AuthorRepository;
import ru.otus.testing.model.Author;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findById() {
        var author = authorRepository.findById(1L);

        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals(1, presentAuthor.getId());
        assertEquals("Andrey", presentAuthor.getName());
        assertEquals(46, presentAuthor.getYear());
    }

    @Test
    void findByNameAndYear() {
        var author = authorRepository.findByNameAndYear("Andrey", 46);

        assertNotNull(author);
        assertEquals(1, author.getId());
        assertEquals("Andrey", author.getName());
        assertEquals(46, author.getYear());
    }

    @Test
    void save() {
        var saveAuthor = authorRepository.save(new Author("AAAAAA", 50L));

        var dbAuthor = em.find(Author.class, 3);

        assertEquals(dbAuthor.getId(), saveAuthor.getId());
        assertEquals(dbAuthor.getName(), saveAuthor.getName());
        assertEquals(dbAuthor.getYear(), saveAuthor.getYear());
    }

    @Test
    @Rollback
    void deleteById() {
        var dbAuthor = em.find(Author.class, 1);

        assertEquals(1, dbAuthor.getId());
        assertEquals("Andrey", dbAuthor.getName());
        assertEquals(46, dbAuthor.getYear());

        authorRepository.deleteById(1L);

        assertNull(em.find(Author.class, 1));
    }
}
