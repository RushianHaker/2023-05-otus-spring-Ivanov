package ru.otus.testing.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.service.BookService;

@Controller
@RequestMapping("/book")
public class BookPageController {
    private final BookService bookService;

    public BookPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({""})
    public String getAllBooks() {
        return "booklist";
    }

    @GetMapping({"/infobook/{bookId}"})
    public String infoBookPage(@PathVariable("bookId") long bookId, Model model) {
        BookDTO book = bookService.findById(bookId);
        model.addAttribute("book", book);
        return "infobook";
    }

    @GetMapping({"/addbook"})
    public String addBookPage() {
        return "addbook";
    }

    @GetMapping({"/editbook/{bookId}"})
    public String editBookPage(@PathVariable("bookId") long bookId, Model model) {
        BookDTO book = bookService.findById(bookId);
        model.addAttribute("book", book);
        return "editbook";
    }
}
