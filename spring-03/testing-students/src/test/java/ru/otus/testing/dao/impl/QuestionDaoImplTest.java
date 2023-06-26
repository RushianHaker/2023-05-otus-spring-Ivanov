package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.testing.config.AppProps;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@SpringBootTest
class QuestionDaoImplTest {

    private QuestionDao questionDao;

    @BeforeEach
    public void init() {
        AppProps appProps = mock(AppProps.class);
        MessageSource messageSource = mock(MessageSource.class);
        questionDao = new QuestionDaoImpl(messageSource, appProps);
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
