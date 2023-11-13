package ru.otus.testing.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "authors_name", nullable = false)
    private String name;

    @Column(name = "author_year", nullable = false)
    private long year;

    public Author(String name, long year) {
        this.name = name;
        this.year = year;
    }
}
