package ru.otus.testing.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String title;

    private String author;

    private String genre;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }
}
