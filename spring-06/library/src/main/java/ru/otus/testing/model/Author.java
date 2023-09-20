package ru.otus.testing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "authors_name", nullable = false)
    private String name;

    @Column(name = "author_year", nullable = false)
    private long year;

    public Author(String name, long year) {
        this.name = name;
        this.year = year;
    }

    public Author(long id, String name, long year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Author() {

    }
}
