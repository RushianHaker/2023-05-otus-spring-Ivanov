package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.testing.dao.AuthorRepository;
import ru.otus.testing.dao.BookRepository;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.IOService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;

    private final IOService ioService;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository, CommentRepository commentRepository, IOService ioService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.ioService = ioService;
    }

    @Transactional
    @Override
    public Book save(String bookName, long bookYear, Author author, Genre genre, List<Comment> commentsList) {
        var authorInfoFromDb = findOrCreateAuthor(author);
        var genreInfoFromDb = findOrCreateGenre(genre);
        var commentsInfoFromDbList = findOrCreateComments(commentsList);

        return bookRepository.save(new Book(bookName, bookYear, authorInfoFromDb, genreInfoFromDb,
                commentsInfoFromDbList));
    }

    @Override
    public Book findById(long bookId) {
        var bookInfo = bookRepository.findById(bookId);

        if (bookInfo.isPresent()) {
            var presentedBookInfo = bookInfo.get();
            ioService.outputString(
                    "Book-" + presentedBookInfo.getId() + ")" +
                            " id: " + presentedBookInfo.getId() +
                            ", name: " + presentedBookInfo.getName() +
                            ", year: " + presentedBookInfo.getYear() +
                            ", authors : " + presentedBookInfo.getAuthor() +
                            ", genres: " + presentedBookInfo.getGenre() +
                            ", comments: " + presentedBookInfo.getComment());

            return presentedBookInfo;
        }
        return new Book();
    }

    @Override
    public List<Book> findAll() {
        var booksList = bookRepository.findAll();

        ioService.outputString("Books info list (size: " + booksList.size() + "): ");
        for (var bookInfo : booksList) {
            ioService.outputString(
                    "Book-" + bookInfo.getId() + ")" +
                            " id: " + bookInfo.getId() +
                            ", name: " + bookInfo.getName() +
                            ", year: " + bookInfo.getYear() +
                            ", authors : " + bookInfo.getAuthor() +
                            ", genres: " + bookInfo.getGenre() +
                            ", comments: " + bookInfo.getComment());
        }
        return booksList;
    }

    @Transactional
    @Override
    public void update(long bookId, String bookName, long bookYear, Author author, Genre genre,
                       List<Comment> commentsList) {
        var findBook = bookRepository.findById(bookId);

        if (findBook.isPresent()) {
            var authorInfoFromDb = findOrCreateAuthor(author);
            var genreInfoFromDb = findOrCreateGenre(genre);
            var commentsInfoFromDbList = findOrCreateComments(commentsList);

            var presentFindBook = findBook.get();
            presentFindBook.setName(bookName);
            presentFindBook.setYear(bookYear);
            presentFindBook.setAuthor(authorInfoFromDb);
            presentFindBook.setGenre(genreInfoFromDb);
            presentFindBook.setComment(commentsInfoFromDbList);

            bookRepository.save(presentFindBook);
        }
    }

    @Override
    public void delete(long bookId) {
        bookRepository.deleteById(bookId);
    }


    /**
     * Проверяет наличие автора в БД и при его отсутствии - создает
     *
     * @param author - автор книги
     * @return возвращает модель автора - либо из БД, либо созданную и сохраненную
     */
    private Author findOrCreateAuthor(Author author) {
        var authorInfoFromDb = authorRepository.findByNameAndYear(author.getName(), author.getYear());
        if (authorInfoFromDb == null) {
            authorInfoFromDb = authorRepository.save(author);
        }
        return authorInfoFromDb;
    }

    /**
     * Проверяет наличие жанра в БД и при его отсутствии - создает
     *
     * @param genre - жанр книги
     * @return возвращает модель жанра - либо из БД, либо созданный и сохраненный
     */
    private Genre findOrCreateGenre(Genre genre) {
        var genreInfoFromDb = genreRepository.findByName(genre.getName());
        if (genreInfoFromDb == null) {
            genreInfoFromDb = genreRepository.save(genre);
        }
        return genreInfoFromDb;
    }

    /**
     * Проверяет наличие комментариев в БД и при их отсутствии - создает
     *
     * @param commentsList - комментарии у книги
     * @return возвращает лист комментариев - либо из БД, либо созданный и сохраненный
     */
    private List<Comment> findOrCreateComments(List<Comment> commentsList) {
        var commentsInfoFromDbList = commentRepository.findByCommentsTextList(commentsList.stream()
                .map(Comment::getCommentText).toList());
        if (commentsInfoFromDbList.isEmpty()) {
            for (var comment : commentsList) {
                commentsInfoFromDbList.add(commentRepository.save(comment));
            }
        }

        return commentsInfoFromDbList;
    }
}
