package ru.otus.testing.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.testing.model.User;
import ru.otus.testing.service.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService {

    private final int completeCount;

    public TestResultServiceImpl(@Value("${application.completeCount}") int completeCount) {
        this.completeCount = completeCount;
    }

    @Override
    public void checkTestResult(User user, int result) {
        if (result >= completeCount) {
            System.out.println(user.getName() + " " + user.getLastName() +
                    " you PASS the test with " + result + " points !");
        } else {
            System.out.println(user.getName() + " " + user.getLastName() +
                    " you NOT PASS the test, your result is " + result + " !");
        }
    }
}
