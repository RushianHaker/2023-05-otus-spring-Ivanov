package ru.otus.testing.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "book_name", nullable = false)
    private String name;

    @Column(name = "book_year", nullable = false)
    private Long year;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;


    public Book(String name, Long year, Author author, Genre genre) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }
}
