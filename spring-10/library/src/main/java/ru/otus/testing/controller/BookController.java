package ru.otus.testing.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/book"})
    public String getAllBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }

    @PostMapping({"/addbook"})
    public String addBook(@NotNull String bookName, long bookYear, @NotNull String authorName, long authorYear,
                          @NotNull String genreName, Model model) {
        var author = new Author(authorName, authorYear);
        var genre = new Genre(genreName);

        Book saved = bookService.save(bookName, bookYear, author, genre);
        model.addAttribute(saved);
        return "redirect:/book";
    }

    @PatchMapping({"/editbook"})
    public String editBook(long bookId, @NotNull String bookName, long bookYear, @NotNull String authorName,
                           long authorYear, @NotNull String genreName, Model model) {
        var author = new Author(authorName, authorYear);
        var genre = new Genre(genreName);

        bookService.update(bookId, bookName, bookYear, author, genre);
        model.addAttribute(bookService.findById(bookId));
        return "redirect:/book";
    }

    @DeleteMapping({"/delbook"})
    public String deleteBookById(@RequestParam("id") long id) {
        bookService.delete(id);
        return "redirect:/book";
    }


    @GetMapping({"/book/infobook"})
    public String infoPageBook(@RequestParam("id") long id, Model model) {
        BookDTO book = bookService.findById(id);
        model.addAttribute("book", book);
        return "infobook";
    }

    @GetMapping({"/book/addbook"})
    public String addPageBook() {
        return "addbook";
    }

    @GetMapping({"/book/editbook"})
    public String editPageBook(@RequestParam("id") long id, Model model) {
        BookDTO book = bookService.findById(id);
        model.addAttribute("book", book);
        return "editbook";
    }

    @GetMapping({"/book/delbook"})
    public String delPageBook(@RequestParam("id") long id, Model model) {
        BookDTO book = bookService.findById(id);
        model.addAttribute("book", book);
        return "delbook";
    }
}
