package ru.otus.testing.service.impl;

import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Question;
import ru.otus.testing.service.TestService;
import ru.otus.testing.service.PrintService;

import java.util.List;


public class TestServiceImpl implements TestService {
    private final QuestionDao questionDao;

    private final PrintService printService;

    public TestServiceImpl(QuestionDao questionDao, PrintService printService) {
        this.questionDao = questionDao;
        this.printService = printService;
    }

    public List<Question> getTest() {
        return questionDao.findAll();
    }

    public void printTest() {
        for (var question : getTest()) {
            printService.print("\n" + "Question: " + question.getQuestion());
            printService.print("Answers:");
            for (var answer : question.getAnswer()) {
                printService.print("* " + answer.getAnswer());
            }
        }
    }
}
