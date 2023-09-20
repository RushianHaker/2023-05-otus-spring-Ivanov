package ru.otus.testing.commandline;

import jakarta.validation.constraints.NotNull;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.CommentService;
import ru.otus.testing.service.ConvertModelInfoToStringService;

@ShellComponent
public class BookCommands {

    private final BookService bookService;

    private final CommentService commentService;

    private final ConvertModelInfoToStringService convertModelInfoToStringService;

    public BookCommands(BookService bookService, CommentService commentService,
                        ConvertModelInfoToStringService convertModelInfoToStringService) {
        this.bookService = bookService;
        this.commentService = commentService;
        this.convertModelInfoToStringService = convertModelInfoToStringService;
    }

    @ShellMethod(value = "readById-book", key = {"readById-book", "-rbi-book"})
    public String readBookById(long bookId) {
        var bookDTO = bookService.findById(bookId);
        return convertModelInfoToStringService.convertBookInfoToString(bookDTO);
    }

    @ShellMethod(value = "readAll-book", key = {"readAll-book", "-rall-book"})
    public String readAllBook() {
        var booksList = bookService.findAll();
        return convertModelInfoToStringService.convertListBooksInfoToString(booksList);
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
        var bookDTO = bookService.findById(bookId);

        commentService.saveBooksComment(new Comment(commentText, new Book(bookDTO.getId(), bookDTO.getName(),
                bookDTO.getYear(), bookDTO.getAuthor(), bookDTO.getGenre(), bookDTO.getComments())));
        return "Comment of bookDTO was saved";
    }
}
