package ru.otus.testing.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private long id;

    private final String name;

    private final Long year;

    public Author(String name, Long year) {
        this.name = name;
        this.year = year;
    }

    public Author(long id, String name, Long year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }
}
