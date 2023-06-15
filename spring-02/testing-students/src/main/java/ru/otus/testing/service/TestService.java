package ru.otus.testing.service;


import ru.otus.testing.model.Answer;

import java.util.List;

public interface TestService {

    void printTest();

    void testing();

    int askUserAnswer();

    boolean checkUserAnswer(int userAnswer, List<Answer> answersList);
}
