package ru.otus.testing.commandline;

import jakarta.validation.constraints.NotNull;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import java.util.Arrays;

@ShellComponent
public class BookCommands {

    private final BookService bookService;

    public BookCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "readById-book", key = {"readById-book", "-rbi-book"})
    public String readBookById(long bookId) {
        var presentedBookInfo = bookService.findById(bookId);

        return "Book info: " +
                " id: " + presentedBookInfo.getId() +
                ", name: " + presentedBookInfo.getName() +
                ", year: " + presentedBookInfo.getYear() +
                ", author : " + presentedBookInfo.getAuthor() +
                ", genre: " + presentedBookInfo.getGenre() +
                ", comments: " + presentedBookInfo.getComment().stream().map(Comment::getCommentText).toList();
    }

    @ShellMethod(value = "readAll-book", key = {"readAll-book", "-rall-book"})
    public String readAllBook() {
        var booksList = bookService.findAll();

        var stringBuilder = new StringBuilder("Books info list (size: " + booksList.size() + "): ");

        for (var bookInfo : booksList) {
            stringBuilder
                    .append("Book-").append(bookInfo.getId()).append(")")
                    .append(" id: ").append(bookInfo.getId())
                    .append(", name: ").append(bookInfo.getName())
                    .append(", year: ").append(bookInfo.getYear())
                    .append(", author : ").append(bookInfo.getAuthor().getName())
                    .append(", genre: ").append(bookInfo.getGenre().getName())
                    .append(", comments: ").append(bookInfo.getComment().stream().map(Comment::getCommentText).toList());
        }

        return stringBuilder.toString();
    }

    @ShellMethod(value = "create-book", key = {"create-book", "-c-book"})
    public String createBook(@NotNull String bookName, long bookYear, @NotNull String authorName,
                             long authorYear, @NotNull String genreName, @NotNull String[] comments) {
        var author = new Author(authorName, authorYear);
        var genre = new Genre(genreName);

        var commentsList = Arrays.stream(comments).map(Comment::new).toList();

        bookService.save(bookName, bookYear, author, genre, commentsList);
        return "Book was created";
    }

    @ShellMethod(value = "update-book", key = {"update-book", "-u-book"})
    public String updateBook(@NotNull long bookId, @NotNull String bookName, long bookYear, @NotNull String authorName,
                             long authorYear, @NotNull String genreName, @NotNull String[] comments) {
        var author = new Author(authorName, authorYear);
        var genre = new Genre(genreName);

        var commentsList = Arrays.stream(comments).map(Comment::new).toList();

        bookService.update(bookId, bookName, bookYear, author, genre, commentsList);
        return "Info about book was updated";
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "-d-book"})
    public String deleteBook(long bookId) {
        bookService.delete(bookId);
        return "Book was deleted";
    }
}
