package ru.otus.testing.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.testing.dao.AuthorRepository;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.CheckDbFillingService;
import ru.otus.testing.service.IOService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BookServiceImpl.class})
class TestServiceImplBook {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CheckDbFillingService checkDbFillingService;
    @MockBean
    private IOService ioService;

    @Autowired
    private BookService service;

    @Test
    void getAll() {
        var book = new Book("war and peace", 4321L, new Author("Tolstoy", 50L),
                new Genre("history"), List.of(new Comment("cool!")));

        when(bookRepository.findAll()).thenReturn(List.of(book));

        service.findAll();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(2)).outputString(captor.capture());

        String actualOutput = captor.getAllValues().stream()
                .collect(Collectors.joining(System.lineSeparator()));

        assertTrue(actualOutput.contains(book.getName()));
        assertTrue(actualOutput.contains(book.getYear().toString()));
    }
}
