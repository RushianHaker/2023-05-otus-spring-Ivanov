package ru.otus.testing.service.impl;

import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Question;
import ru.otus.testing.service.TestService;

import java.util.List;


public class TestServiceImpl implements TestService {
    private final QuestionDao questionDao;

    public TestServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getTestList() {
        return questionDao.findAll();
    }
}
