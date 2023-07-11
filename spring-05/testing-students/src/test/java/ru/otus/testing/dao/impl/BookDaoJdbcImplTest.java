package ru.otus.testing.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.testing.dao.BookDao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//todo
class BookDaoJdbcImplTest {

    private BookDao bookDao;

    @BeforeEach
    public void init() {
        bookDao = new BookDaoJdbcImpl("test_en.csv");
    }

    @Test
    void printTest() {
        var answer1 = new Answer("Santa", true);
        var answer2 = new Answer("Lenin", false);
        var question = new Question("HO-HO-HO Who i am?", List.of(answer1, answer2));

        var questions = bookDao.findAll();
        assertNotNull(questions);

        var questionDaoList = bookDao.findAll();
        assertNotNull(questionDaoList);

        assertEquals(questionDaoList.get(0).getQuestion(), question.getQuestion());
        assertNotNull(questionDaoList.get(0).getAnswer());

        var answersDaoList = questionDaoList.get(0).getAnswer();
        assertEquals(answersDaoList.get(0).getAnswer(), question.getAnswer().get(0).getAnswer());
        assertEquals(answersDaoList.get(1).getAnswer(), question.getAnswer().get(1).getAnswer());
    }
}
