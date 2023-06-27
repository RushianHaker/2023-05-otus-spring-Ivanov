package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.UserService;
import ru.otus.testing.service.TestService;
import ru.otus.testing.service.TestResultService;
import ru.otus.testing.service.MessageSourceService;

import java.util.InputMismatchException;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final QuestionDao questionDao;

    private final IOService ioService;

    private final UserService userService;

    private final TestResultService testResultService;

    private final MessageSourceService messageSourceService;


    public TestServiceImpl(QuestionDao questionDao, IOService ioService, UserService userService,
                           TestResultService testResultService, MessageSourceService messageSourceService) {
        this.questionDao = questionDao;
        this.ioService = ioService;
        this.userService = userService;
        this.testResultService = testResultService;
        this.messageSourceService = messageSourceService;
    }


    public void testing() {
        int correctAnswers = 0;
        var user = userService.handShakeWithUser();

        for (var question : questionDao.findAll()) {
            ioService.outputString(messageSourceService.getMessage("print_question",
                    new String[]{question.getQuestion()}));
            ioService.outputString(messageSourceService.getMessage("print_answers", null));

            var answersList = question.getAnswer();
            for (var answer : answersList) {
                ioService.outputString(answersList.indexOf(answer) + " - " + answer.getAnswer());
            }

            var userAnswer = askUserAnswer();
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
                userAnswer = ioService.readIntWithPrompt(
                        messageSourceService.getMessage("ask_user_to_write_answer_number", null));
                correct = true;
            } catch (InputMismatchException e) {
                ioService.readNextWithPrompt(messageSourceService.getMessage("incorrect_user_answer", null));
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
