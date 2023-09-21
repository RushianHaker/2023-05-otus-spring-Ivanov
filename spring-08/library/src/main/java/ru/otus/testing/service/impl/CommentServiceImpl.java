package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.model.Comment;
import ru.otus.testing.service.CommentService;

import java.util.ArrayList;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void saveBooksComment(Comment comment) {
        var book = comment.getBook();
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
            comment.setBook(book);
        }

        repository.save(comment);
    }
}
