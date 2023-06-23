package ru.otus.testing.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.TestResultService;
import ru.otus.testing.service.TestService;
import ru.otus.testing.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class TestServiceImplTest {
    private QuestionDao questionDao;
    private IOService printService;
    private TestService service;

    @BeforeEach
    void setUp() {
        questionDao = mock(QuestionDao.class);
        printService = mock(IOService.class);
        UserService userService = mock(UserService.class);
        TestResultService testResultService = mock(TestResultService.class);
        service = new TestServiceImpl(questionDao, printService, userService, testResultService);
    }

    @Test
    void printTest() {
        var answer1 = new Answer("test answer 1", true);
        var answer2 = new Answer("test answer 2", false);
        var question = new Question("test question", List.of(answer1, answer2));

        when(questionDao.findAll()).thenReturn(List.of(question));

        service.testing();
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(printService, times(4)).outputString(captor.capture());

        String actualOutput = captor.getAllValues().stream()
                .collect(Collectors.joining(System.lineSeparator()));
        assertTrue(actualOutput.contains(question.getQuestion()));
        assertTrue(actualOutput.contains(answer1.getAnswer()));
        assertTrue(actualOutput.contains(answer2.getAnswer()));
    }
}
