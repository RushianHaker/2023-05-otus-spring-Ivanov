package ru.otus.testing.controller.rest;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<BookDTO> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping({"/{id}"})
    public BookDTO infoPageBook(@PathVariable long id) {
        return bookService.findById(id);
    }

    @PostMapping()
    public void addBook(@RequestBody @NotNull BookDTO book) {
        bookService.save(book.getName(), book.getYear(),
                new Author(book.getAuthor().getName(), book.getAuthor().getYear()),
                new Genre(book.getGenre().getName()));
    }

    @PutMapping({"/{id}"})
    public void editBook(@PathVariable long id, @RequestBody @NotNull BookDTO book) {
        bookService.update(id, book.getName(), book.getYear(),
                new Author(book.getAuthor().getName(), book.getAuthor().getYear()),
                new Genre(book.getGenre().getName()));
    }

    @DeleteMapping({"/{id}"})
    public void deleteBookById(@PathVariable("id") long id) {
        bookService.delete(id);
    }
}
