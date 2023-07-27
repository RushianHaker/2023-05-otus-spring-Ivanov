package ru.otus.testing.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private final String name;
    private final long year;
    private long id;

    public Author(String name, long year) {
        this.name = name;
        this.year = year;
    }

    public Author(long id, String name, long year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
}
