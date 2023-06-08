package ru.otus.testing.service.impl;

import org.junit.jupiter.api.Test;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;
import ru.otus.testing.service.TestService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TestServiceImplTest {
    QuestionDao questionDao = mock(QuestionDao.class);
    TestService service = new TestServiceImpl(questionDao, new PrintServiceImpl());

    @Test
    void getTestListTest() {
        when(questionDao.findAll()).thenReturn(
                List.of(new Question("test question",
                        List.of(
                                new Answer("test answer 1", true),
                                new Answer("test answer 2", false))
                )));

        var listQuesting = service.getTest();

        assertEquals(1, listQuesting.size());
        assertEquals(2, listQuesting.get(0).getAnswer().size());

        assertEquals("test question", listQuesting.get(0).getQuestion());

        assertEquals("test answer 1", listQuesting.get(0).getAnswer().get(0).getAnswer());
        assertTrue(listQuesting.get(0).getAnswer().get(0).isCorrect());

        assertEquals("test answer 2", listQuesting.get(0).getAnswer().get(1).getAnswer());
        assertFalse(listQuesting.get(0).getAnswer().get(1).isCorrect());
    }
}
