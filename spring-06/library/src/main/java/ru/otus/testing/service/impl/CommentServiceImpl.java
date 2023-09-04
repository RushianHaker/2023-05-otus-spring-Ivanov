package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.model.Comment;
import ru.otus.testing.service.CommentService;

import java.util.ArrayList;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void saveBooksComment(Comment comment) {
        var book = comment.getBook();
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
            comment.setBook(book);
        }

        commentDao.save(comment);
    }
}
