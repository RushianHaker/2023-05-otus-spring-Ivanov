package ru.otus.testing.mongock.changelog;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Profile;
import ru.otus.testing.dao.AuthorRepository;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

@Profile("test")
@ChangeLog
public class DatabaseChangeLog {
    @ChangeSet(order = "01", id = "dropDb-01-test", author = "max ivanov", runAlways = true)
    public void dropDb(MongoDatabase md) {
        md.drop();
    }

    @ChangeSet(order = "02", id = "insertIntoDb-01-test", author = "max ivanov", runAlways = true)
    public void insertIntoDb(AuthorRepository authorRepository, GenreRepository genreRepository,
                             BookRepository bookRepository, CommentRepository commentRepository) {
        Author alexanderPushkin = authorRepository.save(new Author("100", "Alex", 22L));
        Author abab = authorRepository.save(new Author("300", "Abab", 33L));

        Genre poem = genreRepository.save(new Genre("100", "History"));
        Genre holy = genreRepository.save(new Genre("300", "Holy"));

        Book eugeneOnegin = bookRepository.save(new Book("100", "Max", 333L, alexanderPushkin, poem));
        Book andreyOnTree = bookRepository.save(new Book("300", "Andrey on tree", 555L, abab, holy));

        commentRepository.save(new Comment("100", "I like that book !", eugeneOnegin));
        commentRepository.save(new Comment("300", "I don't love this author !", andreyOnTree));
    }
}
