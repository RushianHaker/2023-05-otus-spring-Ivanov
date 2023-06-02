package ru.otus.testing.service;


import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;

import java.util.ArrayList;
import java.util.HashMap;

public interface TestService {
    HashMap<Question, ArrayList<Answer>> getTestList();
}
