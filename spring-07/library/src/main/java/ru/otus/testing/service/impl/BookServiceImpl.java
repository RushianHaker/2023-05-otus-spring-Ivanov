package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.CheckDbFillingService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final CheckDbFillingService checkDbFillingService;

    public BookServiceImpl(BookRepository bookRepository, CheckDbFillingService checkDbFillingService) {
        this.bookRepository = bookRepository;
        this.checkDbFillingService = checkDbFillingService;
    }

    @Override
    public Book findById(long bookId) {
        var bookInfo = bookRepository.findById(bookId);
        return bookInfo.orElseGet(Book::new);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Book save(String bookName, long bookYear, Author author, Genre genre, List<Comment> commentsList) {
        var authorInfoFromDb = checkDbFillingService.findOrCreateAuthor(author);
        var genreInfoFromDb = checkDbFillingService.findOrCreateGenre(genre);
        var commentsInfoFromDbList = checkDbFillingService.findOrCreateComments(commentsList);

        return bookRepository.save(new Book(bookName, bookYear, authorInfoFromDb, genreInfoFromDb,
                commentsInfoFromDbList));
    }

    @Transactional
    @Override
    public void update(long bookId, String bookName, long bookYear, Author author, Genre genre,
                       List<Comment> commentsList) {
        var findBook = bookRepository.findById(bookId);

        if (findBook.isPresent()) {
            var authorInfoFromDb = checkDbFillingService.findOrCreateAuthor(author);
            var genreInfoFromDb = checkDbFillingService.findOrCreateGenre(genre);
            var commentsInfoFromDbList = checkDbFillingService.findOrCreateComments(commentsList);

            var presentFindBook = findBook.get();
            presentFindBook.setName(bookName);
            presentFindBook.setYear(bookYear);
            presentFindBook.setAuthor(authorInfoFromDb);
            presentFindBook.setGenre(genreInfoFromDb);

            List<Comment> commentList = new ArrayList<>(commentsInfoFromDbList);
            presentFindBook.setComment(commentList);

            bookRepository.save(presentFindBook);
        }
    }

    @Override
    public void delete(long bookId) {
        bookRepository.deleteById(bookId);
    }
}
