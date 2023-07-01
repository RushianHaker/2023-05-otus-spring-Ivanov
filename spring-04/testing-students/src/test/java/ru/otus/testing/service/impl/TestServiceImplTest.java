package ru.otus.testing.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;
import ru.otus.testing.model.User;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.MessageSourceService;
import ru.otus.testing.service.TestResultService;
import ru.otus.testing.service.TestService;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class TestServiceImplTest {
    @MockBean
    TestResultService testResultService;
    @MockBean
    private QuestionDao questionDao;
    @MockBean
    private IOService ioService;
    @MockBean
    private MessageSourceService messageSourceService;
    @Autowired
    private TestService service;


    @Test
    void printTest() {
        var answer1 = new Answer("test answer 1", true);
        var answer2 = new Answer("test answer 2", false);
        var question = new Question("test question", List.of(answer1, answer2));

        when(questionDao.findAll()).thenReturn(List.of(question));
        when(messageSourceService.getMessage("print_question", new String[]{question.getQuestion()}))
                .thenReturn(question.getQuestion());

        service.testing(new User("a", "b"));

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(4)).outputString(captor.capture());

        String actualOutput = captor.getAllValues().stream()
                .collect(Collectors.joining(System.lineSeparator()));
        assertTrue(actualOutput.contains(question.getQuestion()));
        assertTrue(actualOutput.contains(answer1.getAnswer()));
        assertTrue(actualOutput.contains(answer2.getAnswer()));
    }
}
