package ru.otus.testing.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "books")
@AllArgsConstructor
@NamedEntityGraph(name = "otus-book-author-genre-entity-graph",
        attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

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

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.LAZY)
    private List<Comment> comments;


    public Book(String name, Long year, Author author, Genre genre, List<Comment> comments) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public Book(long id, String name, Long year, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name, Long year, Author author, Genre genre) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public Book() {

    }
}
