package ru.otus.testing.service.impl;


import org.springframework.stereotype.Service;
import ru.otus.testing.model.User;
import ru.otus.testing.service.PrintService;
import ru.otus.testing.service.UserService;

import java.util.Scanner;

@Service
public class UserServiceImpl implements UserService {

    private final PrintService printService;

    private final Scanner scanner;

    public UserServiceImpl(PrintService printService) {
        this.printService = printService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public User handShakeWithUser() {
        printService.print("\n" + "Hello, please write your name: ");
        var name = scanner.next();
        printService.print("Ok, now please write your last name: ");
        var lastName = scanner.next();

        var user = new User(name, lastName);
        printService.print("\n" + "Hello, " + user.getName() + " " + user.getLastName() + " !");
        return user;
    }
}