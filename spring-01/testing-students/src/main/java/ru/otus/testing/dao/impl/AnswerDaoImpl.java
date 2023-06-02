package ru.otus.testing.dao.impl;

import ru.otus.testing.dao.AnswerDao;
import ru.otus.testing.model.Answer;

public class AnswerDaoImpl implements AnswerDao {

    public Answer createAnswer(String name) {
        return new Answer(name);
    }
}
