package ru.otus.testing.exceptions;


public class BookServiceException extends IllegalArgumentException {

    /**
     * Constructs an {@code IllegalArgumentException} with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public BookServiceException(String s) {
        super(s);
    }
}
