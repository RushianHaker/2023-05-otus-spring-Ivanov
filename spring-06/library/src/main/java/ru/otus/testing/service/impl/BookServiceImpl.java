package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    private final CommentDao commentDao;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao, CommentDao commentDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.commentDao = commentDao;
    }

    @Transactional
    @Override
    public Book save(String bookName, long bookYear, Author author, Genre genre, List<Comment> commentsList) {
        var authorInfoFromDb = authorDao.findByNameAndYear(author.getName(), author.getYear());
        if (authorInfoFromDb == null) {
            authorInfoFromDb = authorDao.save(author);
        }

        var genreInfoFromDb = genreDao.findByName(genre.getName());
        if (genreInfoFromDb == null) {
            genreInfoFromDb = genreDao.save(genre);
        }

        var commentsInfoFromDbList = commentDao.findByIdAndCommentText(commentsList);
        if (commentsInfoFromDbList.isEmpty()) {
            for (var comment : commentsList) {
                commentsInfoFromDbList.add(commentDao.save(comment));
            }
        }

        return bookDao.save(new Book(bookName, bookYear, authorInfoFromDb, genreInfoFromDb, commentsInfoFromDbList));
    }

    @Override
    public Book findById(long bookId) {
        var bookInfo = bookDao.findById(bookId);
        return bookInfo.orElseGet(Book::new);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Transactional
    @Override
    public void update(long bookId, String bookName, long bookYear, Author author, Genre genre,
                       List<Comment> commentsList) {
        var authorInfoFromDb = authorDao.findByNameAndYear(author.getName(), author.getYear());
        if (authorInfoFromDb == null) {
            authorInfoFromDb = authorDao.save(author);
        }

        var genreInfoFromDb = genreDao.findByName(genre.getName());
        if (genreInfoFromDb == null) {
            genreInfoFromDb = genreDao.save(genre);
        }

        var commentsInfoFromDbList = commentDao.findByIdAndCommentText(commentsList);
        if (commentsInfoFromDbList.isEmpty()) {
            for (var comment : commentsList) {
                commentsInfoFromDbList.add(commentDao.save(comment));
            }
        }

        bookDao.updateById(new Book(bookId, bookName, bookYear, authorInfoFromDb,
                genreInfoFromDb, commentsInfoFromDbList));
    }

    @Override
    public void delete(long bookId) {
        bookDao.deleteById(bookId);
    }
}
