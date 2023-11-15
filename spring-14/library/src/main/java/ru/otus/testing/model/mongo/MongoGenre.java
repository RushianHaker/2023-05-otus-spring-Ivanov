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
@Document(collection = "genres")
public class MongoGenre {
    @Id
    private String id;

    private String name;

    public MongoGenre(String name) {
        this.name = name;
    }
}