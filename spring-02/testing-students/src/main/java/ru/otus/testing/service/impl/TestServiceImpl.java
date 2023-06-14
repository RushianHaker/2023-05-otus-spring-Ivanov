package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.service.PrintService;
import ru.otus.testing.service.TestResultService;
import ru.otus.testing.service.TestService;
import ru.otus.testing.service.UserService;

@Service
public class TestServiceImpl implements TestService {
    private final QuestionDao questionDao;

    private final PrintService printService;

    private final UserService userService;

    private final TestResultService testResultService;

    public TestServiceImpl(QuestionDao questionDao, PrintService printService, UserService userService, TestResultService testResultService) {
        this.questionDao = questionDao;
        this.printService = printService;
        this.userService = userService;
        this.testResultService = testResultService;
    }

    public void printTest() {
        for (var question : questionDao.findAll()) {
            printService.print("\n" + "Question: " + question.getQuestion());
            printService.print("Answers:");
            var answersList = question.getAnswer();
            for (var answer : answersList) {
                printService.print(answersList.indexOf(answer) + " - " + answer.getAnswer());
            }
        }
    }

    public void testing() {
        int correctAnswers = 0;
        var user = userService.handShakeWithUser();
        printTest();

        for (var question : questionDao.findAll()) {

            var userAnswer = userService.askUserAnswer();
            var answersList = question.getAnswer();

            if (userService.checkUserAnswer(userAnswer, answersList)) {
                correctAnswers++;
            }
        }

        testResultService.checkTestResult(user, correctAnswers);
    }
}
