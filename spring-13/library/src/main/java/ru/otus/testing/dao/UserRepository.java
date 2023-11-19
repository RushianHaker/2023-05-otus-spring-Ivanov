package ru.otus.testing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.testing.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
