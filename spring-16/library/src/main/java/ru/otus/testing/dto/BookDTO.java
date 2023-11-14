package ru.otus.testing.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    private long id;

    private String name;

    private Long year;

    private Author author;

    private Genre genre;

    @JsonIgnore
    private List<Comment> comments;

    public BookDTO(long id, String name, Long year, Author author, Genre genre, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

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


    public static BookDTO toDto(Book book) {
        return new BookDTO(book.getId(), book.getName(), book.getYear(), book.getAuthor(), book.getGenre());
    }

    public Book toDomainObject() {
        return new Book(id, name, year, author, genre,
                (comments != null && !comments.isEmpty()) ? new ArrayList<>(comments) : new ArrayList<>());
    }
}
