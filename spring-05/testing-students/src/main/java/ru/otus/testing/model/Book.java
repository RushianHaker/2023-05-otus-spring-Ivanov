package ru.otus.testing.model;


public record Book(long id, String name, Long year, Author author, Genre genre) {
}
