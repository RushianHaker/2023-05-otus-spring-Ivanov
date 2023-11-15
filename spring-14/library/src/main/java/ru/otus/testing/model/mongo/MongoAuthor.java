package ru.otus.testing.model.mongo;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
public class MongoAuthor {
    @Id
    private String id;

    private String name;

    private long year;

    public MongoAuthor(String name, long year) {
        this.name = name;
        this.year = year;
    }
}
