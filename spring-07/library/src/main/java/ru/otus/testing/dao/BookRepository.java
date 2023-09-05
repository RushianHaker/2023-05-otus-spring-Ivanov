package ru.otus.testing.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.testing.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = {"author", "genre", "comments"})
    Optional<Book> findById(long bookId);

    @EntityGraph(attributePaths = {"author", "genre", "comments"})
    List<Book> findAll();
}
