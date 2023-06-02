package ru.otus.testing.dao;

import ru.otus.testing.model.Question;

public interface QuestionDao {
    Question createQuestion(String name);
}
