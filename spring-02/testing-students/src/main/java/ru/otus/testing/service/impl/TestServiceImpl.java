package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.TestResultService;
import ru.otus.testing.service.TestService;
import ru.otus.testing.service.UserService;

import java.util.InputMismatchException;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final QuestionDao questionDao;

    private final IOService ioService;

    private final UserService userService;

    private final TestResultService testResultService;

    public TestServiceImpl(QuestionDao questionDao, IOService ioService,
                           UserService userService, TestResultService testResultService) {
        this.questionDao = questionDao;
        this.ioService = ioService;
        this.userService = userService;
        this.testResultService = testResultService;
    }

    private void printTest() {
        for (var question : questionDao.findAll()) {
            ioService.outputString("\n" + "Question: " + question.getQuestion());
            ioService.outputString("Answers:");
            var answersList = question.getAnswer();
            for (var answer : answersList) {
                ioService.outputString(answersList.indexOf(answer) + " - " + answer.getAnswer());
            }
        }
    }

    public void testing() {
        int correctAnswers = 0;
        var user = userService.handShakeWithUser();
        printTest();

        for (var question : questionDao.findAll()) {
            var userAnswer = askUserAnswer();
            var answersList = question.getAnswer();
            if (checkUserAnswer(userAnswer, answersList)) {
                correctAnswers++;
            }
        }
        testResultService.checkTestResult(user, correctAnswers);
    }

    private int askUserAnswer() {
        boolean correct = false;
        int userAnswer = 0;

        while (!correct) {
            try {
                userAnswer = ioService.readIntWithPrompt("\n" + "Write your answer NUMBER: ");
                correct = true;
            } catch (InputMismatchException e) {
                ioService.readNextWithPrompt("Incorrect. Please try again !");
            }
        }
        return userAnswer;
    }

    private boolean checkUserAnswer(int userAnswer, List<Answer> answersList) {
        for (var answer : answersList) {
            if (answer.isCorrect()) {
                if (answersList.indexOf(answer) == userAnswer) {
                    return true;
                }
            }
        }
        return false;
    }
}
