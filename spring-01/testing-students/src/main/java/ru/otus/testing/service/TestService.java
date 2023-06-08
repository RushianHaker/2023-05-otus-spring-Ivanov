package ru.otus.testing.service;


import ru.otus.testing.model.Question;

import java.util.List;

public interface TestService {
    List<Question> getTest();

    void printTest();
}
