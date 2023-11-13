package ru.otus.testing.model.mongo;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class MongoBook {
    @Id
    private String id;

    private String name;

    private Long year;

    @DBRef
    private MongoAuthor mongoAuthor;

    @DBRef
    private MongoGenre mongoGenre;

    public MongoBook(String name, Long year, MongoAuthor mongoAuthor, MongoGenre mongoGenre) {
        this.name = name;
        this.year = year;
        this.mongoAuthor = mongoAuthor;
        this.mongoGenre = mongoGenre;
    }

    public MongoBook(String id) {
        this.id = id;
    }
}
