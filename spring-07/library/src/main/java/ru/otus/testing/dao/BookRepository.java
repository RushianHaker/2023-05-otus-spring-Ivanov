package ru.otus.testing.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.testing.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = {"author", "genre"})
    Optional<Book> findById(long bookId);

    @EntityGraph(attributePaths = {"author", "genre"})
    @Query(value = "select distinct b from Book b join fetch b.comment")
    List<Book> findAll();
}
