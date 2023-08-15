package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.model.Genre;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

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
        genreRepository.save(new Genre("AAAAAA"));

        var genre = genreRepository.findById(3L);

        assertTrue(genre.isPresent());

        var presentComment = genre.get();
        assertEquals(3, presentComment.getId());
        assertEquals("AAAAAA", presentComment.getName());
    }

    @Test
    @Rollback
    void deleteById() {
        var genre = genreRepository.findById(1L);

        assertTrue(genre.isPresent());

        var presentComment = genre.get();
        assertEquals(1, presentComment.getId());
        assertEquals("comedy", presentComment.getName());

        genreRepository.deleteById(1L);

        assertTrue(genreRepository.findById(1L).isEmpty());
    }
}
