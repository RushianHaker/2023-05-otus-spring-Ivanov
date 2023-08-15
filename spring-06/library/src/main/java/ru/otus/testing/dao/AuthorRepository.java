package ru.otus.testing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Genre;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByNameAndYear(String name, long year);
}
