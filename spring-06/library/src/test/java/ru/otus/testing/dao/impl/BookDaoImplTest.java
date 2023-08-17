package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Import({BookDaoImpl.class})
@DataJpaTest
class BookDaoImplTest {
    @Autowired
    private BookDao bookDao;

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

        assertEquals(1, preasentBook.getComment().get(0).getId());
        assertEquals("I can write better!", preasentBook.getComment().get(0).getCommentText());
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

        assertEquals(1, preasentBook.getComment().get(0).getId());
        assertEquals("I can write better!", preasentBook.getComment().get(0).getCommentText());
    }

    @Test
    void save() {
        var author = new Author("aaa", 1111);

        var genre = new Genre("bbb");

        var comment = new Comment("ccc");
        var commentsList = Collections.singletonList(comment);

        var book = new Book("war and peace", 4321L, author, genre, commentsList);

        bookDao.save(book);
        var bookById = bookDao.findById(3);
        assertTrue(bookById.isPresent());

        var presentBook = bookById.get();
        assertEquals(3, presentBook.getId());
        assertEquals("war and peace", presentBook.getName());
        assertEquals(4321, presentBook.getYear());

        assertEquals("aaa", presentBook.getAuthor().getName());
        assertEquals(1111, presentBook.getAuthor().getYear());

        assertEquals("bbb", presentBook.getGenre().getName());

        assertEquals("ccc", presentBook.getComment().get(0).getCommentText());
    }

    @Test
    @Rollback
    void update() {
        bookDao.updateById(new Book(1, "Tolstoy Tolstoy Tolstoy", 1111L,
                new Author("AAAAA", 1111), new Genre("AAAAA"),
                List.of(new Comment("AAAAA"))));

        var book = bookDao.findById(1);
        assertTrue(book.isPresent());

        var presentBook = book.get();
        assertEquals("Tolstoy Tolstoy Tolstoy", presentBook.getName());
        assertEquals(1111, presentBook.getYear());

        assertEquals("AAAAA", presentBook.getGenre().getName());

        assertEquals("AAAAA", presentBook.getAuthor().getName());
        assertEquals(1111, presentBook.getAuthor().getYear());

        assertEquals("AAAAA", presentBook.getComment().get(0).getCommentText());
    }

    @Test
    @Rollback
    void deleteById() {
        var book = bookDao.findAll();
        assertEquals(1, book.size());
        assertEquals(1, book.get(0).getId());
        assertEquals("Test Book", book.get(0).getName());

        bookDao.deleteById(1);

        assertEquals(0, bookDao.findAll().size());
    }
}
