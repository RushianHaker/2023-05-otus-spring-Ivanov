package ru.otus.testing.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genre {
    private final String name;
    private long id;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
