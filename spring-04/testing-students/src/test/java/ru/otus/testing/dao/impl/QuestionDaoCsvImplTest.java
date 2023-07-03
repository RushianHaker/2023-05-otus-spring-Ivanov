package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class QuestionDaoCsvImplTest {

    @Autowired
    private QuestionDao questionDao;


    @Test
    void printTest() {
        var answer1 = new Answer("Santa", true);
        var answer2 = new Answer("Lenin", false);
        var question = new Question("HO-HO-HO Who i am?", List.of(answer1, answer2));

        var questions = questionDao.findAll();
        assertNotNull(questions);

        var questionDaoList = questionDao.findAll();
        assertNotNull(questionDaoList);

        assertEquals(questionDaoList.get(0).getQuestion(), question.getQuestion());
        assertNotNull(questionDaoList.get(0).getAnswer());

        var answersDaoList = questionDaoList.get(0).getAnswer();
        assertEquals(answersDaoList.get(0).getAnswer(), question.getAnswer().get(0).getAnswer());
        assertEquals(answersDaoList.get(1).getAnswer(), question.getAnswer().get(1).getAnswer());
    }
}
