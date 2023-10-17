package ru.otus.testing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.testing.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByNameAndYear(String name, long year);
}
