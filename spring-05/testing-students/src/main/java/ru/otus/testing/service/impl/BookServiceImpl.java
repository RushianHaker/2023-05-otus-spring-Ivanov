package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.UserAnswerService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    private final IOService ioService;

    private final UserAnswerService userAnswerService;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao, IOService ioService,
                           UserAnswerService userAnswerService) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.ioService = ioService;
        this.userAnswerService = userAnswerService;
    }

    @Transactional
    @Override
    public Book create() {
        ioService.outputString("Enter books info, please:");

        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswer("- Enter book written year: ");

        var authorName = ioService.readNextWithPrompt("- Enter author name: ");
        var authorYear = userAnswerService.checkUserAnswer("- Enter author years: ");

        var genreName = ioService.readNextWithPrompt("- Enter books genre name: ");

        Genre genre = genreDao.create(new Genre(genreName));
        Author author = authorDao.create(new Author(authorName, authorYear));
        return bookDao.create(new Book(bookName, bookYear, author, genre));
    }

    @Override
    public Book readById() {
        ioService.outputString("Enter books id, that info you want to see: ");
        var bookId = userAnswerService.checkUserAnswer("- Enter book id: ");

        var bookInfo = bookDao.getById(bookId);
        ioService.outputString(
                "Book-" + bookInfo.getId() + ")" +
                        " id: " + bookInfo.getId() +
                        ", name: " + bookInfo.getName() +
                        ", year: " + bookInfo.getYear() +
                        ", author: " + bookInfo.getAuthor().getName() +
                        ", author years: " + bookInfo.getAuthor().getYear() +
                        ", genre: " + bookInfo.getGenre().getName());

        return bookInfo;
    }

    @Override
    public List<Book> readAll() {
        var booksList = bookDao.getAll();

        ioService.outputString("Books info list (size: " + booksList.size() + "): ");
        for (var bookInfo : booksList) {
            ioService.outputString(
                    "Book-" + bookInfo.getId() + ")" +
                            " id: " + bookInfo.getId() +
                            ", name: " + bookInfo.getName() +
                            ", year: " + bookInfo.getYear() +
                            ", author: " + bookInfo.getAuthor().getName() +
                            ", author years: " + bookInfo.getAuthor().getYear() +
                            ", genre: " + bookInfo.getGenre().getName());
        }
        return booksList;
    }

    @Transactional
    @Override
    public void update() {
        ioService.outputString("Enter books info, please: ");

        var bookId = userAnswerService.checkUserAnswer("- Enter book id, that you want update: ");

        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswer("- Enter book written year: ");

        var authorName = ioService.readNextWithPrompt("- Enter author name: ");
        var authorYear = userAnswerService.checkUserAnswer("- Enter author years: ");

        var genreName = ioService.readNextWithPrompt("- Enter books genre name: ");

        //получение id's из таблицы books
        var bookDaoById = bookDao.getById(bookId);

        genreDao.update(new Genre(genreName), bookDaoById.getGenre().getId());
        authorDao.update(new Author(authorName, authorYear), bookDaoById.getAuthor().getId());

        bookDao.update(bookName, bookYear, bookId);
    }

    @Override
    public void delete() {
        var bookId = userAnswerService.checkUserAnswer("- Enter book id, that you want delete: ");
        bookDao.deleteById(bookId);
    }
}
