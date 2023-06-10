package ru.otus.testing.service.impl;

import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.service.PrintService;
import ru.otus.testing.service.TestService;


public class TestServiceImpl implements TestService {
    private final QuestionDao questionDao;

    private final PrintService printService;

    public TestServiceImpl(QuestionDao questionDao, PrintService printService) {
        this.questionDao = questionDao;
        this.printService = printService;
    }

    public void printTest() {
        for (var question : questionDao.findAll()) {
            printService.print("\n" + "Question: " + question.getQuestion());
            printService.print("Answers:");
            for (var answer : question.getAnswer()) {
                printService.print("* " + answer.getAnswer());
            }
        }
    }
}
