package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.service.PrintService;
import ru.otus.testing.service.TestResultService;
import ru.otus.testing.service.TestService;
import ru.otus.testing.service.UserService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
public class TestServiceImpl implements TestService {
    private final QuestionDao questionDao;

    private final PrintService printService;

    private final UserService userService;

    private final TestResultService testResultService;

    private final Scanner scanner;

    public TestServiceImpl(QuestionDao questionDao, PrintService printService,
                           UserService userService, TestResultService testResultService) {
        this.questionDao = questionDao;
        this.printService = printService;
        this.userService = userService;
        this.testResultService = testResultService;
        this.scanner = new Scanner(System.in);
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
            var userAnswer = askUserAnswer();
            var answersList = question.getAnswer();
            if (checkUserAnswer(userAnswer, answersList)) {
                correctAnswers++;
            }
        }
        testResultService.checkTestResult(user, correctAnswers);
    }

    @Override
    public int askUserAnswer() {
        boolean correct = false;
        int userAnswer = 0;

        while (!correct) {
            try {
                printService.print("\n" + "Write your answer NUMBER: ");
                userAnswer = scanner.nextInt();
                correct = true;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect. Please try again !");
                scanner.next();
            }
        }
        return userAnswer;
    }

    @Override
    public boolean checkUserAnswer(int userAnswer, List<Answer> answersList) {
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
