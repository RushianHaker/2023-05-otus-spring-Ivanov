package ru.otus.testing.repository;


import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.testing.model.Book;

public interface BookRepository extends ReactiveCrudRepository<Book, String> {
    Mono<Book> save(@NotNull Book book);

    Mono<Book> findById(@NotNull String bookId);

    Flux<Book> findAll();
}
