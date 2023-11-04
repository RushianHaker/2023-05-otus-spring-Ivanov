package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.model.Genre;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findById() {
        var genre = genreRepository.findById(1L);

        assertTrue(genre.isPresent());

        var presentGenre = genre.get();
        assertEquals(1, presentGenre.getId());
        assertEquals("comedy", presentGenre.getName());
    }

    @Test
    void findByName() {
        var genre = genreRepository.findByName("comedy");

        assertNotNull(genre);
        assertEquals(1, genre.getId());
        assertEquals("comedy", genre.getName());
    }

    @Test
    void save() {
        var saveGenre = genreRepository.save(new Genre("AAAAAA"));
        var genre = em.find(Genre.class, 3);

        assertEquals(saveGenre.getId(), genre.getId());
        assertEquals(saveGenre.getName(), genre.getName());
    }

    @Test
    @Rollback
    void deleteById() {
        var genre = em.find(Genre.class, 1);

        assertEquals(1, genre.getId());
        assertEquals("comedy", genre.getName());

        genreRepository.deleteById(1L);

        assertNull(em.find(Genre.class, 1));
    }
}
