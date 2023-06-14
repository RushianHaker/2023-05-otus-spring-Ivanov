package ru.otus.testing.service.impl;


import org.springframework.stereotype.Service;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.User;
import ru.otus.testing.service.PrintService;
import ru.otus.testing.service.UserService;

import java.util.InputMismatchException;
import java.util.List;
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

    @Override
    public int askUserAnswer() {
        boolean correct = false;
        int userAnswer = 0;

        while (!correct) {
            try {
                printService.print("\n" + "Write your answer NUMBER: ");
                userAnswer = scanner.nextInt();
                correct = true;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect. Please try again !");
                scanner.next();
            }
        }
        return userAnswer;
    }

    public boolean checkUserAnswer(int userAnswer, List<Answer> answersList) {
        for (var answer : answersList) {
            if (answer.isCorrect()) {
                if (answersList.indexOf(answer) == userAnswer) {
                    return true;
                }
            }
        }
        return false;
    }
}