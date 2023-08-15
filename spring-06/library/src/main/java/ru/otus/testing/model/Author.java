package ru.otus.testing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
