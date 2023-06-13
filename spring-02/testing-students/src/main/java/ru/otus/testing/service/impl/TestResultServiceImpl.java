package ru.otus.testing.service.impl;

import ru.otus.testing.model.User;
import ru.otus.testing.service.TestResultService;


public class TestResultServiceImpl implements TestResultService {

    private static final int PASS_NUMBER = 4;

    @Override
    public void checkTestResult(User user, int result) {
        if (result >= PASS_NUMBER) {
            System.out.println(user.getName() + " " + user.getLastName() +
                    " you are pass the test with " + result + " points !");
        } else {
            System.out.println(user.getName() + " " + user.getLastName() +
                    " you aren't pass the test, your result is " + result + " !");
        }
    }
}
