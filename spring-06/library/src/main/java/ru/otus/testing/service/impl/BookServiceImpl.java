package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.exceptions.BookServiceException;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO findById(long bookId) {
        var bookInfo = bookDao.findById(bookId).orElseThrow(() -> new BookServiceException("Book not found!"));

        List<Comment> comments = bookInfo.getComments() == null ? new ArrayList<>() : bookInfo.getComments().stream()
                .filter(comment -> comment.getCommentText() != null && !comment.getCommentText().isEmpty())
                .toList();

        return new BookDTO(bookInfo.getId(), bookInfo.getName(), bookInfo.getYear(), bookInfo.getAuthor(),
                bookInfo.getGenre(), comments);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    @Transactional
    public Book save(String bookName, long bookYear, Author author, Genre genre) {
        var authorInfoFromDb = authorDao.findByNameAndYear(author.getName(), author.getYear());
        if (authorInfoFromDb == null) {
            authorInfoFromDb = authorDao.save(author);
        }

        var genreInfoFromDb = genreDao.findByName(genre.getName());
        if (genreInfoFromDb == null) {
            genreInfoFromDb = genreDao.save(genre);
        }

        return bookDao.save(new Book(bookName, bookYear, authorInfoFromDb, genreInfoFromDb));
    }

    @Override
    @Transactional
    public void update(long bookId, String bookName, long bookYear, Author author, Genre genre) {
        var authorInfoFromDb = authorDao.findByNameAndYear(author.getName(), author.getYear());
        if (authorInfoFromDb == null) {
            authorInfoFromDb = authorDao.save(author);
        }

        var genreInfoFromDb = genreDao.findByName(genre.getName());
        if (genreInfoFromDb == null) {
            genreInfoFromDb = genreDao.save(genre);
        }

        bookDao.updateById(new Book(bookId, bookName, bookYear, authorInfoFromDb, genreInfoFromDb));
    }

    @Override
    @Transactional
    public void delete(long bookId) {
        bookDao.deleteById(bookId);
    }
}
