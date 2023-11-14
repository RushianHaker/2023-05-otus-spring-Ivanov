package ru.otus.testing.controller.page;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.CommentService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentPageController.class)
public class CommentPageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;


    @DisplayName("Получение страницы добавления")
    @WithMockUser(
            username = "admin",
            authorities = { "ROLE_ADMIN" }
    )
    @Test
    public void getAddBookPagetTest() throws Exception {
        Mockito.when(bookService.findById(1L)).thenReturn(getBookDTOsForTest());

        mockMvc.perform(get("/comment/addcomment/1")).andExpect(
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
