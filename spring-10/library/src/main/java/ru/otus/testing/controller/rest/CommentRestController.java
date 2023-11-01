package ru.otus.testing.controller.rest;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.testing.dto.CommentDTO;
import ru.otus.testing.model.Comment;
import ru.otus.testing.service.CommentService;

@RestController
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/comment")
    public void addComment(@RequestBody @NotNull CommentDTO comment) {
        commentService.saveBooksComment(new Comment(comment.getCommentText(), comment.getBook().toDomainObject()));
    }
}
