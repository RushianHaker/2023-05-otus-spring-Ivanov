package ru.otus.testing.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookPageController {

    @GetMapping({"/book"})
    public String getAllBooks() {
        return "booklist";
    }

    @GetMapping({"/book/infobook/{bookId}"})
    public String infoBookPage(@PathVariable("bookId") String bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "infobook";
    }

    @GetMapping({"/book/addbook"})
    public String addBookPage(Model model) {
        return "addbook";
    }
}
