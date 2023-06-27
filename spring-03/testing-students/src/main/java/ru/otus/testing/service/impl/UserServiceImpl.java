package ru.otus.testing.service.impl;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.testing.config.ApplicationConfig;
import ru.otus.testing.model.User;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final IOService ioService;

    private final MessageSource messageSource;

    private final ApplicationConfig config;

    public UserServiceImpl(IOService ioService, MessageSource messageSource, ApplicationConfig config) {
        this.ioService = ioService;
        this.messageSource = messageSource;
        this.config = config;
    }

    @Override
    public User handShakeWithUser() {
        var name = ioService.readStringWithPrompt(messageSource.getMessage("user_name", null, config.getLocale()));
        var lastName = ioService.readStringWithPrompt(messageSource.getMessage("user_lastName", null, config.getLocale()));

        var user = new User(name, lastName);
        ioService.outputString(messageSource.getMessage("hand_shake_with_user",
                new String[]{user.getName(), user.getLastName()}, config.getLocale()));
        return user;
    }
}