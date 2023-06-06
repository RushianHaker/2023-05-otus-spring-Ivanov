package ru.otus.testing.dao;

import ru.otus.testing.model.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
