package ru.otus.testing.commandline;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.UserAnswerService;

@ShellComponent
public class BookCommandLine {

    private final BookService bookService;

    private final IOService ioService;

    private final UserAnswerService userAnswerService;

    public BookCommandLine(BookService bookService, IOService ioService, UserAnswerService userAnswerService) {
        this.bookService = bookService;
        this.ioService = ioService;
        this.userAnswerService = userAnswerService;
    }


    @ShellMethod(value = "create-book", key = {"create-book", "-c-book"})
    public String createBook() {
        ioService.outputString("Enter books info, please:");

        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswerToLong("- Enter book written year: ");

        var author = userAnswerService.getAuthorInfo();
        var genre = userAnswerService.getGenreInfo();
        var commentsList = userAnswerService.getListCommentInfo();

        bookService.save(bookName, bookYear, author, genre, commentsList);
        return "Book was created";
    }

    @ShellMethod(value = "readById-book", key = {"readById-book", "-rbi-book"})
    public String readBookById() {
        ioService.outputString("Enter books id, that info you want to see: ");
        var bookId = userAnswerService.checkUserAnswerToLong("- Enter book id: ");

        bookService.findById(bookId);
        return "That was all info about this book";
    }

    @ShellMethod(value = "readAll-book", key = {"readAll-book", "-rall-book"})
    public String readAllBook() {
        bookService.findAll();
        return "That was all books list";
    }

    @ShellMethod(value = "update-book", key = {"update-book", "-u-book"})
    public String updateBook() {
        ioService.outputString("Enter books info, please: ");

        var bookId = userAnswerService.checkUserAnswerToLong("- Enter book id, that you want update: ");

        var bookName = ioService.readNextWithPrompt("- Enter book name: ");
        var bookYear = userAnswerService.checkUserAnswerToLong("- Enter book written year: ");

        var author = userAnswerService.getAuthorInfo();
        var genre = userAnswerService.getGenreInfo();
        var commentsList = userAnswerService.getListCommentInfo();

        bookService.update(bookId, bookName, bookYear, author, genre, commentsList);
        return "Info about book was updated";
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "-d-book"})
    public String deleteBook() {
        var bookId = userAnswerService.checkUserAnswerToLong("- Enter book id, that you want delete: ");

        bookService.delete(bookId);
        return "Book was deleted";
    }
}
