package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.model.Genre;

import static org.junit.jupiter.api.Assertions.*;


@Import({GenreDaoImpl.class})
@DataJpaTest
class GenreDaoImplTest {
    @Autowired
    private GenreDao genreDao;

    @Test
    void findById() {
        var genre = genreDao.findById(1);

        assertTrue(genre.isPresent());

        var presentGenre = genre.get();
        assertEquals(1, presentGenre.getId());
        assertEquals("comedy", presentGenre.getName());
    }

    @Test
    void findByNameAndId() {
        var genre = genreDao.findByName(new Genre("comedy"));

        assertNotNull(genre);
        assertEquals(1, genre.getId());
        assertEquals("comedy", genre.getName());
    }

    @Test
    void save() {
        genreDao.save(new Genre("AAAAAA"));

        var genre = genreDao.findById(3);

        assertTrue(genre.isPresent());

        var presentComment = genre.get();
        assertEquals(3, presentComment.getId());
        assertEquals("AAAAAA", presentComment.getName());
    }

    @Test
    void update() {
        genreDao.updateById(1, new Genre("hello test"));

        var genre = genreDao.findById(1);

        assertTrue(genre.isPresent());

        var presentComment = genre.get();
        assertEquals(1, presentComment.getId());
        assertEquals("hello test", presentComment.getName());
    }

    @Test
    @Rollback
    void deleteById() {
        var genre = genreDao.findById(1);

        assertTrue(genre.isPresent());

        var presentComment = genre.get();
        assertEquals(1, presentComment.getId());
        assertEquals("comedy", presentComment.getName());

        genreDao.deleteById(1);

        assertTrue(genreDao.findById(1).isEmpty());
    }
}
