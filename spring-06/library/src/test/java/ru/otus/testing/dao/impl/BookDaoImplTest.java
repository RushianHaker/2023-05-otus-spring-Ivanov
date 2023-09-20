package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


@Import({BookDaoImpl.class})
@DataJpaTest
class BookDaoImplTest {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private TestEntityManager em;

    @Test
    void findById() {
        var book = bookDao.findById(1);

        assertTrue(book.isPresent());

        var preasentBook = book.get();
        assertEquals(1, preasentBook.getId());
        assertEquals("Test Book", preasentBook.getName());
        assertEquals(1852, preasentBook.getYear());

        assertEquals(1, preasentBook.getGenre().getId());
        assertEquals("comedy", preasentBook.getGenre().getName());

        assertEquals(1, preasentBook.getAuthor().getId());
        assertEquals("Andrey", preasentBook.getAuthor().getName());
        assertEquals(46, preasentBook.getAuthor().getYear());

        assertEquals(1, preasentBook.getComments().get(0).getId());
        assertEquals("I can write better!", preasentBook.getComments().get(0).getCommentText());
    }

    @Test
    void findAll() {
        var book = bookDao.findAll();

        assertEquals(1, book.size());

        var preasentBook = book.get(0);
        assertEquals(1, preasentBook.getId());
        assertEquals("Test Book", preasentBook.getName());
        assertEquals(1852, preasentBook.getYear());

        assertEquals(1, preasentBook.getGenre().getId());
        assertEquals("comedy", preasentBook.getGenre().getName());

        assertEquals(1, preasentBook.getAuthor().getId());
        assertEquals("Andrey", preasentBook.getAuthor().getName());
        assertEquals(46, preasentBook.getAuthor().getYear());
    }

    @Test
    void save() {
        var author = new Author("aaa", 1111);
        var genre = new Genre("bbb");
        var comment = new Comment("ccc", new Book());
        var commentsList = Collections.singletonList(comment);
        var book = new Book("war and peace", 4321L, author, genre, commentsList);

        var saveBook = bookDao.save(book);
        var bookById = em.find(Book.class, 2);

        assertEquals(saveBook.getId(), bookById.getId());
        assertEquals(saveBook.getName(), bookById.getName());
        assertEquals(saveBook.getYear(), bookById.getYear());

        assertEquals(saveBook.getAuthor().getName(), bookById.getAuthor().getName());
        assertEquals(saveBook.getAuthor().getYear(), bookById.getAuthor().getYear());

        assertEquals(saveBook.getGenre().getName(), bookById.getGenre().getName());

        assertEquals(saveBook.getComments().get(0).getCommentText(), bookById.getComments().get(0).getCommentText());
    }

    @Test
    @Rollback
    void update() {
        bookDao.updateById(new Book(1, "Tolstoy Tolstoy Tolstoy", 1111L,
                new Author("AAAAA", 1111), new Genre("AAAAA")));

        var book = em.find(Book.class, 1);
        assertEquals("Tolstoy Tolstoy Tolstoy", book.getName());
        assertEquals(1111, book.getYear());

        assertEquals("AAAAA", book.getGenre().getName());

        assertEquals("AAAAA", book.getAuthor().getName());
        assertEquals(1111, book.getAuthor().getYear());
    }

    @Test
    @Rollback
    void deleteById() {
        var book = em.find(Book.class, 1);
        assertEquals("Test Book", book.getName());
        assertEquals(1852, book.getYear());

        bookDao.deleteById(1);

        assertNull(em.find(Book.class, 1));
    }
}
