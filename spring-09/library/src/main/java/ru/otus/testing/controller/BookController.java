package ru.otus.testing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.AuthorService;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.GenreService;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping({"/book"})
    public String getAllBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }

    @PostMapping({"/addbook"})
    public String addBook(Book book, Model model) {
        Book saved = bookService.save(book.getName(), book.getYear(), book.getAuthor(), book.getGenre());
        model.addAttribute(saved);
        return "redirect:/book";
    }

    @PatchMapping({"/editbook"})
    public String editBook(Book book, Model model) {
        bookService.update(book.getId(), book.getName(), book.getYear(), book.getAuthor(), book.getGenre());
        model.addAttribute(bookService.findById(book.getId()));
        return "redirect:/book";
    }

    @DeleteMapping({"/delbook"})
    public String deleteBookWithCommentsById(@RequestParam("id") long id) {
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
    public String addPageBook(Model model) {
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "addbook";
    }

    @GetMapping({"/book/editbook"})
    public String editPageBook(@RequestParam("id") long id, Model model) {
        BookDTO book = bookService.findById(id);
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
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
