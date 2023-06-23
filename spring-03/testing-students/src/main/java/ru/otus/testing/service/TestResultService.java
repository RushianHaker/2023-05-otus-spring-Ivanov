package ru.otus.testing.service;


import ru.otus.testing.model.User;

public interface TestResultService {

    void checkTestResult(User user, int result);
}
