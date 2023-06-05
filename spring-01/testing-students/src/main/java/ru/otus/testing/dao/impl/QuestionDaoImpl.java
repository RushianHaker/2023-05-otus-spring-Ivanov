package ru.otus.testing.dao.impl;

import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    public Question createQuestion(String name, List<Answer> answerList) {
        return new Question(name, answerList);
    }
}
