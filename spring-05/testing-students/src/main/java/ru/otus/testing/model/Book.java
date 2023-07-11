package ru.otus.testing.model;


public record Book(Long id, String name, Long year, Author author, Genre genre) {
}
