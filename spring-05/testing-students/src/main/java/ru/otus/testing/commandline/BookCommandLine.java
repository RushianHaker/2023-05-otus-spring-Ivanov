package ru.otus.testing.commandline;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.testing.service.BookService;

@ShellComponent
public class BookCommandLine {

    private final BookService bookService;


    public BookCommandLine(BookService bookService) {
        this.bookService = bookService;
    }


    @ShellMethod(value = "create-book", key = {"create-book", "-c-book"})
    public String create() {
        bookService.create();
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
        bookService.update();
        return "Info about book was updated";
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "-d-book"})
    public String delete() {
        bookService.delete();
        return "Book was deleted";
    }
}
