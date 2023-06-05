package ru.otus.testing.dao;

import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;

import java.util.List;

public interface QuestionDao {
    Question createQuestion(String name, List<Answer> answerList);
}
