package ru.otus.testing.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.model.Book;
import ru.otus.testing.service.BookService;

import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Page<Book> findPaginated(Pageable pageable) {
        var startItem = pageable.getPageNumber() * pageable.getPageSize();
        var findedBooksList = bookRepository.findAll();
        var booksSize = findedBooksList.size();

        List<Book> bookList;
        if (booksSize < startItem) {
            bookList = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageable.getPageSize(), booksSize);
            bookList = findedBooksList.subList(startItem, toIndex);
        }
        return new PageImpl<>(bookList, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), booksSize);
    }
}
