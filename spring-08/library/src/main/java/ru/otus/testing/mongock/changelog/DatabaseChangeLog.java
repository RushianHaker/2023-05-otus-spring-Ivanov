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

import java.util.Collections;

@Profile("dev")
@ChangeLog
public class DatabaseChangeLog {
    @ChangeSet(order = "01", id = "dropDb-01", author = "max ivanov", runAlways = true)
    public void dropDb(MongoDatabase md) {
        md.drop();
    }

    @ChangeSet(order = "02", id = "insertIntoDb-01", author = "max ivanov", runAlways = true)
    public void insertIntoDb(AuthorRepository authorRepository, GenreRepository genreRepository,
                             BookRepository bookRepository, CommentRepository commentRepository) {
        Author alexanderPushkin = authorRepository.save(new Author("Alexander Pushkin", 43L));
        Author stevenKing = authorRepository.save(new Author("Steven King", 34L));
        Author levTolstoy = authorRepository.save(new Author("Lev Tolstoy", 65L));

        Genre poem = genreRepository.save(new Genre("Poem"));
        Genre horror = genreRepository.save(new Genre("Horror"));
        Genre novel = genreRepository.save(new Genre("Novel"));

        Book eugeneOnegin = bookRepository.save(new Book("Eugene Onegin", 5555L, alexanderPushkin, poem));
        Book thinner = bookRepository.save(new Book("Thinner", 1111L, stevenKing, horror));
        Book warAndPeace = bookRepository.save(new Book("War and Peace", 1998L, levTolstoy, novel));

        Comment first = commentRepository.save(new Comment("My son like that book !", eugeneOnegin));
        Comment second = commentRepository.save(new Comment("Best that i ever read!", thinner));
        Comment third = commentRepository.save(new Comment("I thin this is not mine, but book not bad", warAndPeace));

        eugeneOnegin.setComments(Collections.singletonList(first));
        thinner.setComments(Collections.singletonList(second));
        warAndPeace.setComments(Collections.singletonList(third));

        bookRepository.save(eugeneOnegin);
        bookRepository.save(thinner);
        bookRepository.save(warAndPeace);
    }
}
