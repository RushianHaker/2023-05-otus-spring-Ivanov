package ru.otus.testing.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.CommentService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CommentServiceImpl.class})
class CommentServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private CommentService commentService;

    @Test
    void save() {
        var book = new Book("war and peace", 4321L, new Author("Tolstoy", 50L),
                new Genre("history"), new ArrayList<>());
        var comment = new Comment("test hello", book);

        when(commentRepository.save(comment)).thenReturn(comment);

        commentService.saveBooksComment(comment);

        ArgumentCaptor<Comment> captorComment = ArgumentCaptor.forClass(Comment.class);
        verify(commentRepository, times(1)).save(captorComment.capture());

        var actualOutputComment = captorComment.getAllValues().get(0);
        assertEquals(actualOutputComment.getCommentText(), comment.getCommentText());
    }
}
