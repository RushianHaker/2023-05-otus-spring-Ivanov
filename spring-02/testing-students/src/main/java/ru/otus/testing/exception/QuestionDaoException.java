package ru.otus.testing.exception;


public class QuestionDaoException extends RuntimeException {
    public QuestionDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
