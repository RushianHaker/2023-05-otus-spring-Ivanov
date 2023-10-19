package ru.otus.testing.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.CommentService;

@Controller
public class CommentController {
    private final CommentService commentService;

    private final BookService bookService;

    public CommentController(CommentService commentService, BookService bookService) {
        this.commentService = commentService;
        this.bookService = bookService;
    }

    @PostMapping({"/addcomment"})
    public String addComment(Book bookDTO, @NotNull String commentText, Model model) {
        Comment saved = commentService.saveBooksComment(new Comment(commentText, new Book(bookDTO.getId(),
                bookDTO.getName(), bookDTO.getYear(), bookDTO.getAuthor(), bookDTO.getGenre(), bookDTO.getComments())));
        model.addAttribute(saved.getBook());
        return "redirect:/book";
    }


    @GetMapping({"/comment/addcomment"})
    public String addPageComment(@RequestParam("id") long id, Model model) {
        BookDTO bookDTO = bookService.findById(id);
        model.addAttribute("bookDTO", bookDTO);
        return "addcomment";
    }
}
