package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.IOService;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final IOService ioService;

    private final UserAnswerServiceImpl userAnswerService;

    public BookServiceImpl(BookDao bookDao, IOService ioService, UserAnswerServiceImpl userAnswerService) {
        this.bookDao = bookDao;
        this.ioService = ioService;
        this.userAnswerService = userAnswerService;
    }

    @Override
    public String create() {
        ioService.outputString("Enter books info, please:");

        var bookId = userAnswerService.checkUserAnswer("- Enter book id: ");
        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswer("- Enter book written year: ");

        var authorName = ioService.readNextWithPrompt("- Enter author name: ");
        var authorYear = userAnswerService.checkUserAnswer("- Enter author years: ");

        var genreName = ioService.readNextWithPrompt("- Enter books genre name: ");

        bookDao.create(new Book(bookId, bookName, bookYear, new Author(authorName, authorYear),
                new Genre(genreName)));

        return "Book name: " + bookName + ", was created";
    }

    @Override
    public String readById() {
        ioService.outputString("Enter books id, that info you want to see: ");

        var bookId = userAnswerService.checkUserAnswer("- Enter book id: ");

        return bookDao.getById(bookId).toString();
    }

    @Override
    public String readAll() {
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

        return "That was all books list";
    }

    @Override
    public String update() {
        ioService.outputString("Enter books info, please: ");

        var bookId = userAnswerService.checkUserAnswer("- Enter book id, that you want update: ");

        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswer("- Enter book written year: ");

        var authorName = ioService.readNextWithPrompt("- Enter author name: ");
        var authorYear = userAnswerService.checkUserAnswer("- Enter author years: ");

        var genreName = ioService.readNextWithPrompt("- Enter books genre name: ");

        bookDao.update(new Book(bookId, bookName, bookYear, new Author(authorName, authorYear),
                new Genre(genreName)), bookId);

        return "Info about book with id: " + bookId + ", was updated";
    }

    @Override
    public String delete() {
        var bookId = userAnswerService.checkUserAnswer("- Enter book id, that you want delete: ");

        bookDao.deleteById(bookId);

        return "Book with id: " + bookId + ", was deleted";
    }
}
