package ru.otus.testing.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @HystrixCommand(commandKey = "waitPage", fallbackMethod = "callWaitPage")
    @Transactional
    public Page<Book> findPaginated(Pageable pageable) {
        randomSleep();

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

        log.info("Get all books in library");

        return new PageImpl<>(bookList, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), booksSize);
    }


    public Page<Book> callWaitPage(Pageable pageable) {
        log.warn("fallBackMethod");

        return new PageImpl<>(List.of(new Book("0", "default book", 0L,
                new Author("0", "default author", 0),
                new Genre("0", "default genre"))),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), 1);
    }

    private void randomSleep() {
        if (new Random().nextInt(5) > 3) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
