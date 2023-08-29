package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.exceptions.BookServiceException;
import ru.otus.testing.model.Comment;
import ru.otus.testing.service.CommentService;

import java.util.ArrayList;

@Service
public class CommentServiceImpl implements CommentService {

    private final BookDao bookDao;

    private final CommentDao commentDao;

    public CommentServiceImpl(BookDao bookDao, CommentDao commentDao) {
        this.bookDao = bookDao;
        this.commentDao = commentDao;
    }

    @Override
    public void saveBooksComment(Comment comment) {
        var book = comment.getBook();

        if (book == null) {
            throw new BookServiceException("Book not found!");
        }

        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
        }

        var commentInfoFromDb = commentDao.save(comment);
        comment.getBook().getComments().add(commentInfoFromDb);

        bookDao.save(book);
    }
}
