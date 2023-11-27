package ru.otus.testing.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.testing.model.Book;
import ru.otus.testing.service.BookService;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "menu";
    }

    @GetMapping("/book")
    public String getLibrary(@RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "10") int size, Model model) {
        Page<Book> books = bookService.findPaginated(PageRequest.of(page, size));
        model.addAttribute("books", books);

        return "booklist";
    }
}
