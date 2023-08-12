package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.model.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Import({GenreDaoImpl.class})
@JdbcTest
class GenreDaoImplTest {
    @Autowired
    private GenreDao genreDao;

    @Test
    void findById() {
        var genre = genreDao.findById(1);

        assertTrue(genre.isPresent());

        var presentGenre = genre.get();
        assertEquals(1, presentGenre.getId());
        assertEquals("Test Book", presentGenre.getName());
    }

    @Test
    void findByNameAndId() {
        var genre = genreDao.findByNameAndId(List.of(new Genre(1, "Hello world")));

        assertEquals(1, genre.size());

        var presentGenre = genre.get(0);
        assertEquals(1, presentGenre.getId());
        assertEquals("Test Book", presentGenre.getName());
    }

    @Test
    void save() {
        genreDao.save(new Genre(2, "history"));

        var genre = genreDao.findById(2);

        assertTrue(genre.isPresent());

        var presentComment = genre.get();
        assertEquals(1, presentComment.getId());
        assertEquals("Test Book", presentComment.getName());
    }

    @Test
    void update() {
        genreDao.updateById(1, new Genre("hello test"));

        var genre = genreDao.findById(1);

        assertTrue(genre.isPresent());

        var presentComment = genre.get();
        assertEquals(1, presentComment.getId());
        assertEquals("Test Book", presentComment.getName());
    }

    @Test
    @Rollback
    void deleteById() {
        var genre = genreDao.findById(1);

        assertTrue(genre.isPresent());

        var presentComment = genre.get();
        assertEquals(1, presentComment.getId());
        assertEquals("Test Book", presentComment.getName());

        genreDao.deleteById(1);

        assertTrue(genreDao.findById(1).isEmpty());
    }
}
