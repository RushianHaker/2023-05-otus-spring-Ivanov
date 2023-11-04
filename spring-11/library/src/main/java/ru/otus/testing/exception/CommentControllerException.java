package ru.otus.testing.exception;


public class CommentControllerException extends IllegalArgumentException {

    /**
     * Constructs an {@code IllegalArgumentException} with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public CommentControllerException(String s) {
        super(s);
    }
}
