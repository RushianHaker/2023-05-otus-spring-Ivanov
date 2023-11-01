package ru.otus.testing.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.service.AuthorService;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.GenreService;

@Controller
public class BookPageController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookPageController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping({"/book"})
    public String getAllBooks() {
        return "booklist";
    }

    @GetMapping({"/book/infobook/{bookId}"})
    public String infoBookPage(@PathVariable("bookId") long bookId, Model model) {
        BookDTO book = bookService.findById(bookId);
        model.addAttribute("book", book);
        return "infobook";
    }

    @GetMapping({"/book/addbook"})
    public String addBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "addbook";
    }

    @GetMapping({"/book/editbook/{bookId}"})
    public String editBookPage(@PathVariable("bookId") long bookId, Model model) {
        BookDTO book = bookService.findById(bookId);
        model.addAttribute("book", book);
        return "editbook";
    }

    @GetMapping({"/book/delbook/{bookId}"})
    public String delPageBook(@PathVariable("bookId") long bookId, Model model) {
        BookDTO book = bookService.findById(bookId);
        model.addAttribute("book", book);
        return "delbook";
    }
}
