package ru.otus.testing.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.IOService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest
class TestServiceImplBook {
    @MockBean
    private BookDao bookDao;
    @MockBean
    private IOService ioService;
    @Autowired
    private BookService service;

    @Test
    void getAll() {
        var book = new Book(0, "war and peace", 4321L,
                new Author("Tolstoy", 50L), new Genre("history"));

        when(bookDao.getAll()).thenReturn(List.of(book));

        service.readAll();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(2)).outputString(captor.capture());

        String actualOutput = captor.getAllValues().stream()
                .collect(Collectors.joining(System.lineSeparator()));

        assertTrue(actualOutput.contains(book.getName()));
        assertTrue(actualOutput.contains(book.getYear().toString()));
        assertTrue(actualOutput.contains(book.getAuthor().getName()));
        assertTrue(actualOutput.contains(book.getAuthor().getYear().toString()));
        assertTrue(actualOutput.contains(book.getGenre().getName()));
    }
}
