package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.service.IOService;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private final PrintStream output;

    private final Scanner input;


    public IOServiceImpl() {
        output = System.out;
        input = new Scanner(System.in);
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }

    @Override
    public long readLong() {
        return input.nextLong();
    }

    @Override
    public long readLongWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLong();
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        outputString(prompt);
        return input.nextLine();
    }

    @Override
    public String readNextWithPrompt(String prompt) {
        outputString(prompt);
        return input.next();
    }
}
