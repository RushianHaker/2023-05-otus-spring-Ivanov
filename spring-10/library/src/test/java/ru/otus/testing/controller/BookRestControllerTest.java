package ru.otus.testing.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import ru.otus.testing.controller.rest.BookRestController;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private final static long TEST_BOOK_ID = 1L;

    @DisplayName("Получение страницы со списком книг")
    @Test
    public void getAllBookListPageTest() throws Exception {
        Mockito.when(bookService.findAll()).thenReturn(getBooksForTest());

        mockMvc.perform(get("/book")).andExpect(status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testBook")));
    }

    @DisplayName("Получение страницы добавления")
    @Test
    public void getAddBookPagetTest() throws Exception {
        mockMvc.perform(get("/book/addbook")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("<form id=\"edit-form\" method=\"post\" action=\"/addbook\">\n")));
    }

    @DisplayName("Получение страницы редактирования")
    @Test
    public void getEditBookPageTest() throws Exception {
        Mockito.when(bookService.findById(TEST_BOOK_ID)).thenReturn(getBookDTOsForTest());

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");

        mockMvc.perform(get("/book/editbook").params(requestParams)).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testBookDTO"))).andExpect(
                content().string(containsString("testAuthorBookDTO"))).andExpect(
                content().string(containsString("horrorBookDTO")));
    }

    @DisplayName("Получение страницы полной информации по книге")
    @Test
    public void getInfoBookPageTest() throws Exception {
        Mockito.when(bookService.findById(TEST_BOOK_ID)).thenReturn(getBookDTOsForTest());

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");

        mockMvc.perform(get("/book/infobook").params(requestParams)).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testBookDTO"))).andExpect(
                content().string(containsString("testAuthorBookDTO")));
    }

    @DisplayName("Получение страницы удаления книги")
    @Test
    public void getDeleteBookPageTest() throws Exception {
        Mockito.when(bookService.findById(TEST_BOOK_ID)).thenReturn(getBookDTOsForTest());

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");

        mockMvc.perform(get("/book/delbook").params(requestParams)).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testBookDTO"))).andExpect(
                content().string(containsString("testAuthorBookDTO")));
    }

    private List<BookDTO>  getBooksForTest() {
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
