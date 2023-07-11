package ru.otus.testing.service.impl;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.IOService;

@ShellComponent
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final IOService ioService;

    private final UserAnswerServiceImpl userAnswerService;

    public BookServiceImpl(BookDao bookDao, IOService ioService, UserAnswerServiceImpl userAnswerService) {
        this.bookDao = bookDao;
        this.ioService = ioService;
        this.userAnswerService = userAnswerService;
    }

/*    @ShellMethod(value = "start-test", key = {"st", "start-test"})
    @ShellMethodAvailability(value = "checkLoginUser")
    public void startTesting() {
        testService.testing(user);
    }*/

/*
    private Availability checkLoginUser() {
        return user == null ? Availability.unavailable(messageSourceService.getMessage("shell_failed_check_login_user", null)) :
                Availability.available();
    }
*/

    @ShellMethod(value = "create-book", key = {"create-book", "-c-book"})
    @Override
    public String create() {
        ioService.outputString("Enter books info, please:");

        var bookId = userAnswerService.checkUserAnswer("- Enter book id: ");
        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswer("- Enter book written year: ");

        var authorId = userAnswerService.checkUserAnswer("- Enter author id: ");
        var authorName = ioService.readNextWithPrompt("- Enter author name: ");
        var authorYear = userAnswerService.checkUserAnswer("- Enter author years: ");

        var genreId = userAnswerService.checkUserAnswer("- Enter books genre id: ");
        var genreName = ioService.readNextWithPrompt("- Enter books genre name: ");

        bookDao.create(new Book(bookId, bookName, bookYear, new Author(authorId, authorName, authorYear),
                new Genre(genreId, genreName)));

        return "Book name: " + bookName + ", was created";
    }

    @ShellMethod(value = "readById-book", key = {"readById-book", "-rbi-book"})
    @Override
    public String readById() {
        ioService.outputString("Enter books id, that info you want to see:");

        var bookId = userAnswerService.checkUserAnswer("- Enter book id: ");

        return bookDao.getById(bookId).toString();
    }

    @ShellMethod(value = "readAll-book", key = {"readAll-book", "-rall-book"})
    @Override
    public String readAll() {
        var booksList = bookDao.getAll();
        ioService.outputString("Books info list: ");
        for (var bookInfo : booksList) {
            ioService.outputString(
                    "Book-" + bookInfo.id() + ")" +
                            " id: " + bookInfo.id() +
                            ", name: " + bookInfo.name() +
                            ", year: " + bookInfo.year() +
                            ", author: " + bookInfo.author().name() +
                            ", genre: " + bookInfo.genre().name());
        }

        return "That was all books list, size - " + booksList.size();
    }

    @ShellMethod(value = "update-book", key = {"update-book", "-u-book"})
    @Override
    public String update() {
        ioService.outputString("Enter books info, please:");

        var bookId = userAnswerService.checkUserAnswer("- Enter book id: ");
        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswer("- Enter book written year: ");

        var authorId = userAnswerService.checkUserAnswer("- Enter author id: ");
        var authorName = ioService.readNextWithPrompt("- Enter author name: ");
        var authorYear = userAnswerService.checkUserAnswer("- Enter author years: ");

        var genreId = userAnswerService.checkUserAnswer("- Enter books genre id: ");
        var genreName = ioService.readNextWithPrompt("- Enter books genre name: ");

        bookDao.update(new Book(bookId, bookName, bookYear, new Author(authorId, authorName, authorYear),
                new Genre(genreId, genreName)), bookId);

        return "Info about book with id: " + bookId + ", was updated";
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "-d-book"})
    @Override
    public String delete() {
        var bookId = userAnswerService.checkUserAnswer("- Enter book id: ");

        bookDao.deleteById(bookId);

        return "Book with id: " + bookId + ", was deleted";
    }
}
