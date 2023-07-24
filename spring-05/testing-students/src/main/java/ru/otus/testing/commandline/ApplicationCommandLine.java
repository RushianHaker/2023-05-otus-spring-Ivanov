package ru.otus.testing.commandline;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.UserAnswerService;

@ShellComponent
public class ApplicationCommandLine {

    private final BookService bookService;

    private final IOService ioService;

    private final UserAnswerService userAnswerService;

    public ApplicationCommandLine(BookService bookService, IOService ioService, UserAnswerService userAnswerService) {
        this.bookService = bookService;
        this.ioService = ioService;
        this.userAnswerService = userAnswerService;
    }


    @ShellMethod(value = "create-book", key = {"create-book", "-c-book"})
    public String create() {
        ioService.outputString("Enter books info, please:");

        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswer("- Enter book written year: ");

        var authorName = ioService.readNextWithPrompt("- Enter author name: ");
        var authorYear = userAnswerService.checkUserAnswer("- Enter author years: ");

        var genreName = ioService.readNextWithPrompt("- Enter books genre name: ");

        bookService.create(bookName, bookYear, authorName, authorYear, genreName);
        return "Book was created";
    }

    @ShellMethod(value = "readById-book", key = {"readById-book", "-rbi-book"})
    public String readById() {
        bookService.readById();
        return "That was all info about this book";
    }

    @ShellMethod(value = "readAll-book", key = {"readAll-book", "-rall-book"})
    public String readAll() {
        bookService.readAll();
        return "That was all books list";
    }

    @ShellMethod(value = "update-book", key = {"update-book", "-u-book"})
    public String update() {
        ioService.outputString("Enter books info, please: ");

        var bookId = userAnswerService.checkUserAnswer("- Enter book id, that you want update: ");

        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswer("- Enter book written year: ");

        var authorName = ioService.readNextWithPrompt("- Enter author name: ");
        var authorYear = userAnswerService.checkUserAnswer("- Enter author years: ");

        var genreName = ioService.readNextWithPrompt("- Enter books genre name: ");

        bookService.update(bookId, bookName, bookYear, authorName, authorYear, genreName);
        return "Info about book was updated";
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "-d-book"})
    public String delete() {
        bookService.delete();
        return "Book was deleted";
    }
}
