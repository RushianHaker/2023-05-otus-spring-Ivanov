package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.AuthorRepository;
import ru.otus.testing.model.Author;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    void findById() {
        var author = authorRepository.findById("100");

        assertTrue(author.isPresent());

        var presentAuthor = author.get();
        assertEquals("100", presentAuthor.getId());
        assertEquals("Alex", presentAuthor.getName());
        assertEquals(22L, presentAuthor.getYear());
    }

    @Test
    void findByNameAndYear() {
        var author = authorRepository.findByNameAndYear("Alex", 22L);

        assertNotNull(author);
        assertEquals("100", author.getId());
        assertEquals("Alex", author.getName());
        assertEquals(22L, author.getYear());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void save() {
        var saveAuthor = authorRepository.save(new Author("200", "AAAAAA", 50L));

        var dbAuthor = mongoOperations.findById("200", Author.class);

        assertNotNull(dbAuthor);

        assertEquals(dbAuthor.getId(), saveAuthor.getId());
        assertEquals(dbAuthor.getName(), saveAuthor.getName());
        assertEquals(dbAuthor.getYear(), saveAuthor.getYear());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteById() {
        var dbAuthor = mongoOperations.findById("300", Author.class);
        assertNotNull(dbAuthor);
        assertEquals("300", dbAuthor.getId());

        authorRepository.deleteById("300");
        assertNull(mongoOperations.findById("300", Author.class));
    }
}
