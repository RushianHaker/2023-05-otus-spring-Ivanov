package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.model.Comment;
import ru.otus.testing.service.CommentService;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void saveBooksComment(Comment comment) {
        var book = comment.getBook();
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
            comment.setBook(book);
        }

        var bookCommentsList  = new ArrayList<>(book.getComments());
        bookCommentsList.add(repository.save(comment));
        book.setComments(bookCommentsList);

        bookRepository.save(book);
    }
}
