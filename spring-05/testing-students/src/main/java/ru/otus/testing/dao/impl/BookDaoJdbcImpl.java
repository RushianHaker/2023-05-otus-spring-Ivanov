package ru.otus.testing.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.dao.impl.mapper.BookMapper;
import ru.otus.testing.model.Book;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbcImpl implements BookDao {
    private final static BookMapper BOOK_MAPPER = new BookMapper();

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void create(Book book) {
        namedParameterJdbcOperations.update("insert into books (id, \"name\", \"year\", author, genre) values (:id, :name, :year)",
                Map.of("id", book.id(), "name", book.name(), "year", book.year(),
                        "author", book.author().name(), "genre", book.genre().name()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, \"name\", \"year\", author, genre from books where id = :id", params, BOOK_MAPPER);
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select id, \"name\", \"year\", author, genre from books", BOOK_MAPPER);
    }

    @Override
    public void update(Book book, long id) {
        namedParameterJdbcOperations.update("update books set id = :id, \"name\" = :name, \"year\" = :year " +
                        ", author = :author, genre = :genre where id = :id",
                Map.of("id", book.id(), "name", book.name(), "year", book.year(),
                        "author", book.author().name(), "genre", book.genre().name()));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }
}