package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuestionDaoCsvImplTest {

    private QuestionDao questionDao;

    @BeforeEach
    public void init() {
        questionDao = new QuestionDaoCsvImpl("test_en.csv");
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
