package ru.otus.testing.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
//todo поправить методы
//todo решить N+1 проблемму
@NamedEntityGraph(name = "otus-student-authors-entity-graph",
        attributeNodes = {@NamedAttributeNode("author")})
@NamedEntityGraph(name = "otus-student-genre-entity-graph",
        attributeNodes = {@NamedAttributeNode("genre")})
@NamedEntityGraph(name = "otus-student-comment-entity-graph",
        attributeNodes = {@NamedAttributeNode("comment")})
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "book_name", nullable = false)
    private String name;

    @Column(name = "book_year", nullable = false)
    private Long year;

    @OneToMany(targetEntity = Author.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private List<Author> author;

    @OneToMany(targetEntity = Genre.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private List<Genre> genre;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private List<Comment> comment;

    public Book(String name, Long year, List<Author> author, List<Genre> genre, List<Comment> comment) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.comment = comment;
    }

    public Book(long id, String name, Long year, List<Author> author, List<Genre> genre, List<Comment> comment) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.comment = comment;
    }

    public Book() {

    }
}
