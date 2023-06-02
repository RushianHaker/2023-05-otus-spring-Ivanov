package ru.otus.testing.dao.impl;

import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Question;

public class QuestionDaoImpl implements QuestionDao {

    public Question createQuestion(String name) {
        return new Question(name);
    }
}
