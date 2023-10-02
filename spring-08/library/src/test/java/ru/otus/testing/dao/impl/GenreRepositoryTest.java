package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.model.Genre;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    void findById() {
        var genre = genreRepository.findById("100");
        assertTrue(genre.isPresent());

        var presentGenre = genre.get();
        assertEquals("100", presentGenre.getId());
        assertEquals("History", presentGenre.getName());
    }

    @Test
    void findByName() {
        var genre = genreRepository.findByName("History");

        assertNotNull(genre);
        assertEquals("100", genre.getId());
        assertEquals("History", genre.getName());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void save() {
        var saveGenre = genreRepository.save(new Genre("200", "AAAAAA"));
        var genre = mongoOperations.findById("200", Genre.class);

        assertNotNull(genre);
        assertEquals(saveGenre.getId(), genre.getId());
        assertEquals(saveGenre.getName(), genre.getName());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void deleteById() {
        var genre = mongoOperations.findById("300", Genre.class);

        assertNotNull(genre);
        assertEquals("300", genre.getId());
        assertEquals("Holy", genre.getName());

        genreRepository.deleteById("300");

        assertNull(mongoOperations.findById("300", Genre.class));
    }
}
