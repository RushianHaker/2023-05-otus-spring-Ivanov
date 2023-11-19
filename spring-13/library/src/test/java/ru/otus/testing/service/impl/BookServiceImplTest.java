package ru.otus.testing.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.testing.dao.AuthorRepository;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BookServiceImpl.class})
class BookServiceImplTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private BookService service;

    @Test
    void save() {
        var book = new Book("war and peace", 4321L, new Author("Tolstoy", 50L), new Genre("history"));

        when(bookRepository.save(book)).thenReturn(book);

        service.save("war and peace", 4321L, new Author("Tolstoy", 50L), new Genre("history"));

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository, times(1)).save(captor.capture());

        var actualOutput = captor.getAllValues().get(0);

        assertEquals(actualOutput.getName(), book.getName());
        assertEquals(actualOutput.getYear(), book.getYear());
    }
}
