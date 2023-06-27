package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;
import ru.otus.testing.service.MessageSourceService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest
class QuestionDaoImplTest {

    private QuestionDao questionDao;

    @BeforeEach
    public void init() {
        MessageSourceService messageSourceService = mock(MessageSourceService.class);
        questionDao = new QuestionDaoImpl(messageSourceService, "test.csv");
    }

    @Test
    void printTest() {
        var answer1 = new Answer("Santa", true);
        var answer2 = new Answer("Lenin", false);
        var question = new Question("HO-HO-HO Who i am?", List.of(answer1, answer2));

        var questions = questionDao.findAll();
        assertNotNull(questions);

        assertThat(questions.get(0)).isEqualToComparingFieldByFieldRecursively(question);
    }
}
