package ru.otus.testing.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BookServiceImpl.class})
class BookServiceImplTest {
    @MockBean
    private BookDao bookDao;
    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;

    @Autowired
    private BookService service;

    @Test
    void save() {
        var book = new Book("war and peace", 4321L, new Author("Tolstoy", 50L), new Genre("history"));

        when(bookDao.save(book)).thenReturn(book);

        service.save("war and peace", 4321L, new Author("Tolstoy", 50L), new Genre("history"));

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookDao, times(1)).save(captor.capture());

        var actualOutput = captor.getAllValues().get(0);

        assertEquals(actualOutput.getName(), book.getName());
        assertEquals(actualOutput.getYear(), book.getYear());
    }
}
