package ru.otus.testing.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Profile;
import ru.otus.testing.model.Book;
import ru.otus.testing.repository.BookRepository;

@Profile("dev")
@ChangeLog
public class DatabaseChangeLog {
    @ChangeSet(order = "001", id = "dropDB", author = "max i", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertBooks", author = "max i", runAlways = true)
    public void insertBooks(MongoDatabase db, BookRepository bookRepository) {
        Book book = new Book();
        book.setTitle("Time to walk");
        book.setAuthor("Andrey Tolmotov");
        book.setGenre("Horror");
        bookRepository.save(book).block();

        book = new Book();
        book.setTitle("Fin of the world");
        book.setAuthor("Hovard Tenesy");
        book.setGenre("Fantasy");
        bookRepository.save(book).block();

        book = new Book();
        book.setTitle("Hello World");
        book.setAuthor("Hot Java");
        book.setGenre("it");
        bookRepository.save(book).block();
    }
}
