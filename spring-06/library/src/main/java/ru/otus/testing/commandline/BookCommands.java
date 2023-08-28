package ru.otus.testing.commandline;

import jakarta.validation.constraints.NotNull;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.PrintBookService;

import java.util.Arrays;

@ShellComponent
public class BookCommands {

    private final BookService bookService;

    private final PrintBookService printBookService;

    public BookCommands(BookService bookService, PrintBookService printBookService) {
        this.bookService = bookService;
        this.printBookService = printBookService;
    }

    @ShellMethod(value = "readById-book", key = {"readById-book", "-rbi-book"})
    public String readBookById(long bookId) {
        var presentedBookInfo = bookService.findById(bookId);
        return printBookService.printBookToConsole(presentedBookInfo);
    }

    @ShellMethod(value = "readAll-book", key = {"readAll-book", "-rall-book"})
    public String readAllBook() {
        var booksList = bookService.findAll();
        return printBookService.printListBooksToConsole(booksList);
    }

    @ShellMethod(value = "create-book", key = {"create-book", "-c-book"})
    public String createBook(@NotNull String bookName, long bookYear, @NotNull String authorName,
                             long authorYear, @NotNull String genreName, @NotNull String[] comments) {
        var author = new Author(authorName, authorYear);
        var genre = new Genre(genreName);

        var commentsList = Arrays.stream(comments).map(comment -> new Comment(comment,
                new Book(bookName, bookYear, author, genre))).toList();

        bookService.save(bookName, bookYear, author, genre, commentsList);
        return "Book was created";
    }

    @ShellMethod(value = "update-book", key = {"update-book", "-u-book"})
    public String updateBook(@NotNull long bookId, @NotNull String bookName, long bookYear, @NotNull String authorName,
                             long authorYear, @NotNull String genreName, @NotNull String[] comments) {
        var author = new Author(authorName, authorYear);
        var genre = new Genre(genreName);

        var commentsList = Arrays.stream(comments).map(comment -> new Comment(comment,
                new Book(bookId, bookName, bookYear, author, genre))).toList();

        bookService.update(bookId, bookName, bookYear, author, genre, commentsList);
        return "Info about book was updated";
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "-d-book"})
    public String deleteBook(long bookId) {
        bookService.delete(bookId);
        return "Book was deleted";
    }

}
