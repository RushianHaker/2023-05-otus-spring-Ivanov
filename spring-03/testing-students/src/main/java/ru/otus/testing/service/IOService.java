package ru.otus.testing.service;


public interface IOService {

    void outputString(String s);

    int readInt();

    int readIntWithPrompt(String prompt);

    String readStringWithPrompt(String prompt);

    String readNextWithPrompt(String prompt);
}
