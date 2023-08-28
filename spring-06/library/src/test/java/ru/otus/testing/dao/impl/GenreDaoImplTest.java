package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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

    @Autowired
    private TestEntityManager em;

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
        var genre = genreDao.findByName("comedy");

        assertNotNull(genre);
        assertEquals(1, genre.getId());
        assertEquals("comedy", genre.getName());
    }

    @Test
    void save() {
        var saveGenre = genreDao.save(new Genre("AAAAAA"));
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

        genreDao.deleteById(1);

        assertNull(em.find(Genre.class, 1));
    }
}
