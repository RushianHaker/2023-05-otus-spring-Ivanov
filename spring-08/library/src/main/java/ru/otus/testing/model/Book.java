package ru.otus.testing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String name;
    private Long year;
    @DBRef
    private Author author;
    @DBRef
    private Genre genre;
    @DBRef
    private List<Comment> comments;


    public Book(String name, Long year, Author author, Genre genre, List<Comment> comments) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public Book(String id, String name, Long year, Author author, Genre genre) {
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
}
