package ru.otus.testing.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.testing.model.User;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService {

    private final IOService ioService;

    private final int completeCount;

    public TestResultServiceImpl(IOService ioService, @Value("${application.completeCount}") int completeCount) {
        this.ioService = ioService;
        this.completeCount = completeCount;
    }

    @Override
    public void checkTestResult(User user, int result) {
        if (result >= completeCount) {
            ioService.outputString(user.getName() + " " + user.getLastName() +
                    " you PASS the test with " + result + " points !");
        } else {
            ioService.outputString(user.getName() + " " + user.getLastName() +
                    " you NOT PASS the test, your result is " + result + " !");
        }
    }
}
