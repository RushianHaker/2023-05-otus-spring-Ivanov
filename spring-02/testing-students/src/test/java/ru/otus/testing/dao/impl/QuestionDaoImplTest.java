package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.testing.config.ApplicationConfig;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class QuestionDaoImplTest {
    private final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    private final QuestionDao questionDao = context.getBean(QuestionDao.class);

    @Test
    void printTest() {
        var answer1 = new Answer("Max", true);
        var answer2 = new Answer("Anna", false);
        var question = new Question("That is your name?", List.of(answer1, answer2));

        var questions = questionDao.findAll();
        assertNotNull(questions);

        assertEquals(question.getQuestion(), questions.get(0).getQuestion());

        assertNotNull(questions.get(0).getAnswer());
        assertEquals(2, questions.get(0).getAnswer().size());

        assertEquals(question.getAnswer().get(0).getAnswer(), questions.get(0).getAnswer().get(0).getAnswer());
        assertEquals(question.getAnswer().get(0).isCorrect(), questions.get(0).getAnswer().get(0).isCorrect());

        assertEquals(question.getAnswer().get(1).getAnswer(), questions.get(0).getAnswer().get(1).getAnswer());
        assertEquals(question.getAnswer().get(1).isCorrect(), questions.get(0).getAnswer().get(1).isCorrect());
    }
}
