package ru.otus.testing.service;

import ru.otus.testing.model.Answer;
import ru.otus.testing.model.User;

import java.util.List;

public interface UserService {

    User handShakeWithUser();

    int askUserAnswer();

    boolean checkUserAnswer(int userAnswer, List<Answer> answersList);
}
