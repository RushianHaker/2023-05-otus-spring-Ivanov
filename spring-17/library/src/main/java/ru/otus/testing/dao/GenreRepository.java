package ru.otus.testing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.testing.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);
}
