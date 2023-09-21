package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.AuthorRepository;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.exception.BookServiceException;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO findById(long bookId) {
        var bookInfo = bookRepository.findById(bookId).orElseThrow(() -> new BookServiceException("Book not found!"));

        List<Comment> comments = bookInfo.getComments() == null ? new ArrayList<>() : bookInfo.getComments().stream()
                .filter(comment -> comment.getCommentText() != null && !comment.getCommentText().isEmpty())
                .toList();

        return new BookDTO(bookInfo.getId(), bookInfo.getName(), bookInfo.getYear(), bookInfo.getAuthor(),
                bookInfo.getGenre(), comments);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book save(String bookName, long bookYear, Author author, Genre genre) {
        var authorInfoFromDb = authorRepository.findByNameAndYear(author.getName(), author.getYear());
        if (authorInfoFromDb == null) {
            authorInfoFromDb = authorRepository.save(author);
        }

        var genreInfoFromDb = genreRepository.findByName(genre.getName());
        if (genreInfoFromDb == null) {
            genreInfoFromDb = genreRepository.save(genre);
        }

        return bookRepository.save(new Book(bookName, bookYear, authorInfoFromDb, genreInfoFromDb));
    }

    @Override
    @Transactional
    public void update(long bookId, String bookName, long bookYear, Author author, Genre genre) {
        var bookDTO = findById(bookId);

        var authorInfoFromDb = authorRepository.findByNameAndYear(author.getName(), author.getYear());
        if (authorInfoFromDb == null) {
            authorInfoFromDb = authorRepository.save(author);
        }

        var genreInfoFromDb = genreRepository.findByName(genre.getName());
        if (genreInfoFromDb == null) {
            genreInfoFromDb = genreRepository.save(genre);
        }

        bookRepository.save(new Book(bookDTO.getId(), bookName, bookYear, authorInfoFromDb,
                genreInfoFromDb, bookDTO.getComments()));
    }

    @Override
    @Transactional
    public void delete(long bookId) {
        bookRepository.deleteById(bookId);
    }
}
