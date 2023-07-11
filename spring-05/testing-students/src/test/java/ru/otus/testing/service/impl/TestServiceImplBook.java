package ru.otus.testing.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.IOService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

//todo
class TestServiceImplBook {
    private BookDao bookDao;
    private IOService ioService;
    private BookService service;
    private MessageSourceService messageSourceService;

    @BeforeEach
    void init() {
        bookDao = mock(BookDao.class);
        ioService = mock(IOService.class);
        UserService userService = mock(UserService.class);
        TestResultService testResultService = mock(TestResultService.class);
        messageSourceService = mock(MessageSourceService.class);
        service = new BookServiceImpl(bookDao, ioService, userService, testResultService, messageSourceService);
    }

    @Test
    void printTest() {
        var answer1 = new Answer("test answer 1", true);
        var answer2 = new Answer("test answer 2", false);
        var question = new Question("test question", List.of(answer1, answer2));

        when(bookDao.findAll()).thenReturn(List.of(question));
        when(messageSourceService.getMessage("print_question", new String[]{question.getQuestion()}))
                .thenReturn(question.getQuestion());

        service.testing();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(4)).outputString(captor.capture());

        String actualOutput = captor.getAllValues().stream()
                .collect(Collectors.joining(System.lineSeparator()));
        assertTrue(actualOutput.contains(question.getQuestion()));
        assertTrue(actualOutput.contains(answer1.getAnswer()));
        assertTrue(actualOutput.contains(answer2.getAnswer()));
    }
}
