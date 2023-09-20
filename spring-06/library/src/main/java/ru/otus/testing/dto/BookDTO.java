package ru.otus.testing.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookDTO {
    private long id;
    private String name;
    private Long year;
    private Author author;
    private Genre genre;
    private List<Comment> comments;

    public BookDTO(String name, Long year, Author author, Genre genre, List<Comment> comments) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public BookDTO(long id, String name, Long year, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public BookDTO(String name, Long year, Author author, Genre genre) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public BookDTO() {

    }
}
