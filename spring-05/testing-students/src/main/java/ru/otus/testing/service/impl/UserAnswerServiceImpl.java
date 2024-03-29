package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.UserAnswerService;

import java.util.InputMismatchException;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    private final IOService ioService;

    public UserAnswerServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public long checkUserAnswer(String msg) {
        boolean correct = false;
        long userAnswer = 0;

        while (!correct) {
            try {
                userAnswer = ioService.readLongWithPrompt(msg);
                correct = true;
            } catch (InputMismatchException e) {
                ioService.readNextWithPrompt("Don't supported symbol, please try again");
            }
        }
        return userAnswer;
    }
}
