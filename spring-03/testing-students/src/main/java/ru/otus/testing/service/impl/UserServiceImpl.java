package ru.otus.testing.service.impl;


import org.springframework.stereotype.Service;
import ru.otus.testing.model.User;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final IOService ioService;

    public UserServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public User handShakeWithUser() {
        var name = ioService.readStringWithPrompt("\n" + "Hello, please write your name: ");
        var lastName = ioService.readStringWithPrompt("Ok, now please write your last name: ");

        var user = new User(name, lastName);
        ioService.outputString("\n" + "Hello, " + user.getName() + " " + user.getLastName() + " !");
        return user;
    }
}