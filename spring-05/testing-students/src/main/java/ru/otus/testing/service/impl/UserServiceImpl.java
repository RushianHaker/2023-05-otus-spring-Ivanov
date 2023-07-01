package ru.otus.testing.service.impl;


import org.springframework.stereotype.Service;
import ru.otus.testing.model.User;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.MessageSourceService;
import ru.otus.testing.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final IOService ioService;

    private final MessageSourceService messageSourceService;


    public UserServiceImpl(IOService ioService, MessageSourceService messageSourceService) {
        this.ioService = ioService;
        this.messageSourceService = messageSourceService;
    }

    @Override
    public User handShakeWithUser() {
        var name = ioService.readStringWithPrompt(messageSourceService.getMessage("user_name", null));
        var lastName = ioService.readStringWithPrompt(messageSourceService.getMessage("user_lastName", null));

        var user = new User(name, lastName);
        ioService.outputString(messageSourceService.getMessage("hand_shake_with_user",
                new String[]{user.getName(), user.getLastName()}));
        return user;
    }
}