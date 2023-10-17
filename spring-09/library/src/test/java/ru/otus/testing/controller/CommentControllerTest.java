package ru.otus.testing.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.CommentService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;


    @DisplayName("Получение страницы добавления")
    @Test
    public void getAddBookPagetTest() throws Exception {
        Mockito.when(bookService.findById(1L)).thenReturn(getBookDTOsForTest());

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");

        mockMvc.perform(get("/comment/addcomment").params(requestParams)).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testBookDTO"))).andExpect(
                content().string(containsString("2222")));
    }


    private BookDTO getBookDTOsForTest() {
        return new BookDTO(1L, "testBookDTO", 2222L,
                new Author(1L, "testAuthorBookDTO", 1111), new Genre(1L, "horrorBookDTO"),
                List.of(new Comment(1L, "testCommentBookDTO", new Book(1L))));
    }
}
