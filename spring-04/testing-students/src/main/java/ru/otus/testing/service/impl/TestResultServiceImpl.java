package ru.otus.testing.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.testing.model.User;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.MessageSourceService;
import ru.otus.testing.service.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService {

    private final IOService ioService;

    private final MessageSourceService messageSourceService;

    private final int completeCount;

    public TestResultServiceImpl(IOService ioService, MessageSourceService messageSourceService,
                                 @Value("${application.completeCount}") int completeCount) {
        this.ioService = ioService;
        this.messageSourceService = messageSourceService;
        this.completeCount = completeCount;
    }

    @Override
    public void checkTestResult(User user, int result) {
        var argsUserInfo = new String[]{user.getName(), user.getLastName(), String.valueOf(result)};

        if (result >= completeCount) {
            ioService.outputString(messageSourceService.getMessage("pass_test", argsUserInfo));
        } else {
            ioService.outputString(messageSourceService.getMessage("failed_test", argsUserInfo));
        }
    }
}
