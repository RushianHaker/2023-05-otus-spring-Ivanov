package ru.otus.testing.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.testing.dao.AuthorRepository;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;

@ChangeLog
public class DatabaseChangeLog {
    @ChangeSet(order = "01", id = "drop-01", author = "max ivanov", runAlways = true)
    public void dropDb(MongoDatabase md) {
        md.drop();
    }

    @ChangeSet(order = "02", id = "insert-01", author = "max ivanov", runAlways = true)
    public void insertIntoDb(AuthorRepository authorRepository, GenreRepository genreRepository,
                             BookRepository bookRepository) {
        Author alexanderPushkin = authorRepository.save(new Author("Alexander Pushkin", 43L));
        Author stevenKing = authorRepository.save(new Author("Steven King", 34L));
        Author levTolstoy = authorRepository.save(new Author("Lev Tolstoy", 65L));

        Genre poem = genreRepository.save(new Genre("Poem"));
        Genre horror = genreRepository.save(new Genre("Horror"));
        Genre novel = genreRepository.save(new Genre("Novel"));

        Book eugeneOnegin = bookRepository.save(new Book("Eugene Onegin", 5555L, alexanderPushkin, poem));
        Book thinner = bookRepository.save(new Book("Thinner", 1111L, stevenKing, horror));
        Book warAndPeace = bookRepository.save(new Book("War and Peace", 1998L, levTolstoy, novel));

        bookRepository.save(eugeneOnegin);
        bookRepository.save(thinner);
        bookRepository.save(warAndPeace);
    }
}
