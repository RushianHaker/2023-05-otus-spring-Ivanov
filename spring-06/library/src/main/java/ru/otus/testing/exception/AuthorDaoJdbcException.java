package ru.otus.testing.exception;


public class AuthorDaoJdbcException extends RuntimeException {
    public AuthorDaoJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
