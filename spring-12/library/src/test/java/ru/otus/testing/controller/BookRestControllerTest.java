package ru.otus.testing.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.testing.controller.page.BookPageController;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.AuthorService;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.GenreService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookPageController.class)
public class BookRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;


    private final static long TEST_BOOK_ID = 1L;

    @DisplayName("Получение страницы со списком книг")
    @WithMockUser(
            username = "admin",
            authorities = { "ROLE_ADMIN" }
    )
    @Test
    public void getAllBookListPageTest() throws Exception {
        Mockito.when(bookService.findAll()).thenReturn(getBooksForTest());

        mockMvc.perform(get("/book")).andExpect(status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("<td> <a href=\\\"/book/infobook/\" + book.id + \"\\\"> <button type=\\\"button\\\">Info</button> </td>")));
    }

    @DisplayName("Получение страницы добавления")
    @WithMockUser(
            username = "admin",
            authorities = { "ROLE_ADMIN" }
    )
    @Test
    public void getAddBookPagetTest() throws Exception {
        mockMvc.perform(get("/book/addbook")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("<input id=\"genre-input\" name=\"genre-input\" type=\"text\"/>")));
    }

    @DisplayName("Получение страницы редактирования")
    @WithMockUser(
            username = "admin",
            authorities = { "ROLE_ADMIN" }
    )
    @Test
    public void getEditBookPageTest() throws Exception {
        Mockito.when(bookService.findById(TEST_BOOK_ID)).thenReturn(getBookDTOsForTest());

        mockMvc.perform(get("/book/editbook/1")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testBookDTO"))).andExpect(
                content().string(containsString("testAuthorBookDTO"))).andExpect(
                content().string(containsString("horrorBookDTO")));
    }

    @DisplayName("Получение страницы полной информации по книге")
    @WithMockUser(
            username = "admin",
            authorities = { "ROLE_ADMIN" }
    )
    @Test
    public void getInfoBookPageTest() throws Exception {
        Mockito.when(bookService.findById(TEST_BOOK_ID)).thenReturn(getBookDTOsForTest());

        mockMvc.perform(get("/book/infobook/1")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testBookDTO"))).andExpect(
                content().string(containsString("testAuthorBookDTO")));
    }

    @DisplayName("Получение страницы удаления книги")
    @WithMockUser(
            username = "admin",
            authorities = { "ROLE_ADMIN" }
    )
    @Test
    public void getDeleteBookPageTest() throws Exception {
        Mockito.when(bookService.findById(TEST_BOOK_ID)).thenReturn(getBookDTOsForTest());

        mockMvc.perform(get("/book/delbook/1")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testBookDTO"))).andExpect(
                content().string(containsString("testAuthorBookDTO")));
    }

    private List<BookDTO> getBooksForTest() {
        return List.of(new BookDTO(1L, "testBook", 2222L,
                new Author(1L, "testAuthor", 1111), new Genre(1L, "horror"),
                List.of(new Comment(1L, "testComment", new Book(1L)))));
    }

    private BookDTO getBookDTOsForTest() {
        return new BookDTO(1L, "testBookDTO", 2222L,
                new Author(1L, "testAuthorBookDTO", 1111), new Genre(1L, "horrorBookDTO"),
                List.of(new Comment(1L, "testCommentBookDTO", new Book(1L))));
    }
}
