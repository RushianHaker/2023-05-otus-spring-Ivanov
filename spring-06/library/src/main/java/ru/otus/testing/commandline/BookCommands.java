package ru.otus.testing.commandline;

import jakarta.validation.constraints.NotNull;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.CommentService;
import ru.otus.testing.service.PrintBookService;

@ShellComponent
public class BookCommands {

    private final BookService bookService;

    private final CommentService commentService;

    private final PrintBookService printBookService;

    public BookCommands(BookService bookService, CommentService commentService, PrintBookService printBookService) {
        this.bookService = bookService;
        this.commentService = commentService;
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
                             long authorYear, @NotNull String genreName) {
        var author = new Author(authorName, authorYear);
        var genre = new Genre(genreName);

        bookService.save(bookName, bookYear, author, genre);
        return "Book was created";
    }

    @ShellMethod(value = "update-book", key = {"update-book", "-u-book"})
    public String updateBook(long bookId, @NotNull String bookName, long bookYear, @NotNull String authorName,
                             long authorYear, @NotNull String genreName) {
        var author = new Author(authorName, authorYear);
        var genre = new Genre(genreName);

        bookService.update(bookId, bookName, bookYear, author, genre);
        return "Info about book was updated";
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "-d-book"})
    public String deleteBook(long bookId) {
        bookService.delete(bookId);
        return "Book was deleted";
    }

    @ShellMethod(value = "save-book-comment", key = {"save-book-comment", "-s-book-c"})
    public String saveBooksComment(long bookId, @NotNull String commentText) {
        var book = bookService.findById(bookId);
        var comment = new Comment(commentText, book);
        commentService.saveBooksComment(comment);
        return "Comment of book was saved";
    }
}
