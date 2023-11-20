package ru.otus.testing.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.testing.model.Book;

public interface BookService {
    Page<Book> findPaginated(Pageable pageable);
}
