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
        return bookService.create();
    }

    @ShellMethod(value = "readById-book", key = {"readById-book", "-rbi-book"})
    public String readById() {
        return bookService.readById();
    }

    @ShellMethod(value = "readAll-book", key = {"readAll-book", "-rall-book"})
    public String readAll() {
        return bookService.readAll();
    }

    @ShellMethod(value = "update-book", key = {"update-book", "-u-book"})
    public String update() {
        return bookService.update();
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "-d-book"})
    public String delete() {
        return bookService.delete();
    }
}
