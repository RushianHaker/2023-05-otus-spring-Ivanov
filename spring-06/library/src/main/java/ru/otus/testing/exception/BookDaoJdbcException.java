package ru.otus.testing.exception;


public class BookDaoJdbcException extends RuntimeException {
    public BookDaoJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
