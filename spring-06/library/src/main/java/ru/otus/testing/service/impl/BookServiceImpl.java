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
import ru.otus.testing.service.IOService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    private final CommentDao commentDao;

    private final IOService ioService;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao, CommentDao commentDao,
                           IOService ioService) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.commentDao = commentDao;
        this.ioService = ioService;
    }

    @Transactional
    @Override
    public Book save(String bookName, long bookYear, List<Author> authorsList, List<Genre> genresList,
                     List<Comment> commentsList) {

        var authorsInfoFromDbList = authorDao.findByNameAndYear(authorsList);

        if (authorsInfoFromDbList.isEmpty()) {
            for (var author : authorsList) {
                authorsInfoFromDbList.add(authorDao.save(author));
            }
        }

        var genresInfoFromDbList = genreDao.findByNameAndId(genresList);

        if (genresInfoFromDbList.isEmpty()) {
            for (var genre : genresList) {
                genresInfoFromDbList.add(genreDao.save(genre));
            }
        }

        var commentsInfoFromDbList = commentDao.findByIdAndCommentText(commentsList);

        if (commentsInfoFromDbList.isEmpty()) {
            for (var comment : commentsList) {
                commentsInfoFromDbList.add(commentDao.save(comment));
            }
        }

        return bookDao.save(new Book(bookName, bookYear, authorsInfoFromDbList, genresInfoFromDbList, commentsInfoFromDbList));
    }

    @Override
    public Book findById(long bookId) {
        var bookInfo = bookDao.findById(bookId);

        if (bookInfo.isPresent()) {
            var presentedBookInfo = bookInfo.get();
            ioService.outputString(
                    "Book-" + presentedBookInfo.getId() + ")" +
                            " id: " + presentedBookInfo.getId() +
                            ", name: " + presentedBookInfo.getName() +
                            ", year: " + presentedBookInfo.getYear() +
                            ", authors : " + presentedBookInfo.getAuthor() +
                            ", genres: " + presentedBookInfo.getGenre() +
                            ", comments: " + presentedBookInfo.getComment());

            return presentedBookInfo;
        }
        return new Book();
    }

    @Override
    public List<Book> findAll() {
        var booksList = bookDao.findAll();

        ioService.outputString("Books info list (size: " + booksList.size() + "): ");
        for (var bookInfo : booksList) {
            ioService.outputString(
                    "Book-" + bookInfo.getId() + ")" +
                            " id: " + bookInfo.getId() +
                            ", name: " + bookInfo.getName() +
                            ", year: " + bookInfo.getYear() +
                            ", authors : " + bookInfo.getAuthor() +
                            ", genres: " + bookInfo.getGenre() +
                            ", comments: " + bookInfo.getComment());
        }
        return booksList;
    }

    @Transactional
    @Override
    public void update(long bookId, String bookName, long bookYear, List<Author> authorsList, List<Genre> genresList,
                       List<Comment> commentsList) {
        var authorsInfoFromDbList = authorDao.findByNameAndYear(authorsList);

        if (authorsInfoFromDbList.isEmpty()) {
            for (var author : authorsList) {
                authorsInfoFromDbList.add(authorDao.save(author));
            }
        }

        var genresInfoFromDbList = genreDao.findByNameAndId(genresList);

        if (genresInfoFromDbList.isEmpty()) {
            for (var genre : genresList) {
                genresInfoFromDbList.add(genreDao.save(genre));
            }
        }

        var commentsInfoFromDbList = commentDao.findByIdAndCommentText(commentsList);

        if (commentsInfoFromDbList.isEmpty()) {
            for (var comment : commentsList) {
                commentsInfoFromDbList.add(commentDao.save(comment));
            }
        }

        bookDao.updateById(bookId, new Book(bookName, bookYear, authorsInfoFromDbList, genresInfoFromDbList, commentsInfoFromDbList));
    }

    @Override
    public void delete(long bookId) {
        bookDao.deleteById(bookId);
    }
}
