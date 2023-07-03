package ru.otus.testing.service;


import jakarta.validation.constraints.NotNull;
import ru.otus.testing.model.User;

public interface TestService {
    void testing(@NotNull User user);
}
