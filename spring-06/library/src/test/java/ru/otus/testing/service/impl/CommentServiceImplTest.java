package ru.otus.testing.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.CommentService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CommentServiceImpl.class})
class CommentServiceImplTest {
    @MockBean
    private BookDao bookDao;
    @MockBean
    private CommentDao commentDao;

    @Autowired
    private CommentService service;

    @Test
    void save() {
        var book = new Book("war and peace", 4321L, new Author("Tolstoy", 50L),
                new Genre("history"), new ArrayList<>());
        var comment = new Comment("test hello", book);

        when(commentDao.save(comment)).thenReturn(comment);

        book.getComments().add(comment);
        when(bookDao.save(book)).thenReturn(book);

        service.saveBooksComment(comment);

        ArgumentCaptor<Comment> captorComment = ArgumentCaptor.forClass(Comment.class);
        verify(commentDao, times(1)).save(captorComment.capture());

        var actualOutputComment = captorComment.getAllValues().get(0);
        assertEquals(actualOutputComment.getCommentText(), comment.getCommentText());


        ArgumentCaptor<Book> captorBook = ArgumentCaptor.forClass(Book.class);
        verify(bookDao, times(1)).save(captorBook.capture());

        var actualOutputBook = captorBook.getAllValues().get(0);

        assertEquals(actualOutputBook.getName(), book.getName());
        assertEquals(actualOutputBook.getYear(), book.getYear());

        assertEquals(actualOutputBook.getComments().get(0).getCommentText(), book.getComments().get(0).getCommentText());
    }
}
