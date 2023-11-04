package ru.otus.testing.service;


import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;

import java.util.List;

public interface BookService {
    Book save(String bookName, long bookYear, Author author, Genre genre);

    BookDTO findById(long bookId);

    List<BookDTO> findAll();

    void update(long bookId, String bookName, long bookYear, Author author, Genre genre);

    void delete(long bookId);
}
