package ru.otus.testing.service;


public interface IOService {

    void outputString(String s);

    long readLong();

    long readLongWithPrompt(String prompt);

    String readStringWithPrompt(String prompt);

    String readNextWithPrompt(String prompt);
}
