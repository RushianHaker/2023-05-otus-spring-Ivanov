package ru.otus.testing.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private final String name;
    private final Long year;
    private final Author author;
    private final Genre genre;
    private long id;

    public Book(String name, Long year, Author author, Genre genre) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public Book(long id, String name, Long year, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }
}
