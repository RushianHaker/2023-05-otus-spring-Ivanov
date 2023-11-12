package ru.otus.testing.controller.rest;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Book;
import ru.otus.testing.repository.BookRepository;

@RestController
@RequestMapping
public class BookRestController {
    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/book")
    public Flux<BookDTO> getAllBooks() {
        return bookRepository.findAll().map(BookDTO::toDto);
    }

    @GetMapping("/api/book/{bookId}")
    public Mono<BookDTO> infoBook(@PathVariable("bookId") String bookId) {
        BookDTO demo = new BookDTO("test", "test", "test", "test");
        return bookRepository.findById(bookId).map(BookDTO::toDto).switchIfEmpty(Mono.fromCallable(() -> demo));
    }

    @PostMapping("/api/book")
    public Mono<Book> addBook(@RequestBody @NotNull Book book) {
        return bookRepository.save(book);
    }
}
