package ru.otus.testing.controller.rest;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public void addComment(@RequestBody @NotNull BookDTO book, @NotNull String commentText) {
        commentService.saveBooksComment(new Comment(commentText, new Book(book.getId(),
                book.getName(), book.getYear(), book.getAuthor(), book.getGenre(), book.getComments())));
    }
}
