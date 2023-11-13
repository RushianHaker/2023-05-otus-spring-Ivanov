package ru.otus.testing.service;

import org.springframework.stereotype.Service;
import ru.otus.testing.model.jpa.Author;
import ru.otus.testing.model.jpa.Book;
import ru.otus.testing.model.jpa.Genre;
import ru.otus.testing.model.mongo.MongoAuthor;
import ru.otus.testing.model.mongo.MongoBook;
import ru.otus.testing.model.mongo.MongoGenre;

import java.util.List;

@Service
public class TransferService {
    public MongoBook transfer(Book book) {
        MongoBook mongoBook = new MongoBook();
        mongoBook.setId(book.getId());
        mongoBook.setName(book.getName());
        mongoBook.setYear(book.getYear());

        MongoAuthor mongoAuthor = new MongoAuthor();
        mongoAuthor.setId(book.getAuthor().getId());
        mongoAuthor.setName(book.getAuthor().getName());
        mongoAuthor.setYear(book.getAuthor().getYear());
        mongoBook.setMongoAuthor(mongoAuthor);

        MongoGenre mongoGenre = new MongoGenre();
        mongoGenre.setId(book.getGenre().getId());
        mongoGenre.setName(book.getGenre().getName());
        mongoBook.setMongoGenre(mongoGenre);

        return mongoBook;
    }

    public MongoAuthor transferAuthor(Author author) {
        return new MongoAuthor(author.getId(), author.getName(), author.getYear());
    }

    public MongoGenre transferGenre(Genre genre) {
        return new MongoGenre(genre.getId(), genre.getName());
    }

}
