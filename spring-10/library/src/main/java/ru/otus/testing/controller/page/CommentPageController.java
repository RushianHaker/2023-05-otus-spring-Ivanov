package ru.otus.testing.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.service.BookService;

@Controller
@RequestMapping("/comment")
public class CommentPageController {
    private final BookService bookService;

    public CommentPageController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping({"/addcomment/{bookId}"})
    public String addCommentPage(@PathVariable("bookId") long bookId, Model model) {
        BookDTO bookDTO = bookService.findById(bookId);
        model.addAttribute("bookDTO", bookDTO);
        return "addcomment";
    }
}
