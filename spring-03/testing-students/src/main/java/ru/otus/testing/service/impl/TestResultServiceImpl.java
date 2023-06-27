package ru.otus.testing.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.testing.config.ApplicationConfig;
import ru.otus.testing.model.User;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService {

    private final IOService ioService;

    private final MessageSource messageSource;

    private final ApplicationConfig config;

    public TestResultServiceImpl(IOService ioService, MessageSource messageSource, ApplicationConfig config) {
        this.ioService = ioService;
        this.messageSource = messageSource;
        this.config = config;
    }

    @Override
    public void checkTestResult(User user, int result) {
        if (result >= config.getCompleteCount()) {
            ioService.outputString(messageSource.getMessage("pass_test",
                    new String[]{user.getName(), user.getLastName(), String.valueOf(result)}, config.getLocale()));
        } else {
            ioService.outputString(messageSource.getMessage("failed_test",
                    new String[]{user.getName(), user.getLastName(), String.valueOf(result)}, config.getLocale()));
        }
    }
}
