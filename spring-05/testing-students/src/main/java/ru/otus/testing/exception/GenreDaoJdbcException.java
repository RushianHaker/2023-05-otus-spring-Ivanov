package ru.otus.testing.exception;


public class GenreDaoJdbcException extends RuntimeException {
    public GenreDaoJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
