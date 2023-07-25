package ru.otus.testing.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.dao.impl.mapper.BookMapper;
import ru.otus.testing.model.Book;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbcImpl implements BookDao {
    private final BookMapper mapper;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbcImpl(BookMapper mapper, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.mapper = mapper;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Book create(Book book) {
        var params = new MapSqlParameterSource();
        params.addValue("book_name", book.getName());
        params.addValue("book_year", book.getYear());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());

        var keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update("insert into books (book_name, book_year, author_id, genre_id) " +
                        "values (:book_name, :book_year, :author_id, :genre_id)", params, keyHolder,
                new String[]{"id"});

        book.setId(keyHolder.getKey().longValue());
        return book;
    }

    @Override
    public Book getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.id, b.book_name, b.book_year, b.author_id, b.genre_id, a.authors_name, a.author_year, " +
                        " g.genres_name from books b " +
                        " left join authors a on a.id = b.author_id " +
                        " left join genres g on g.id = b.genre_id " +
                        "where b.id = :id", params, mapper);
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select b.id, b.book_name, b.book_year, b.author_id, b.genre_id, a.authors_name, a.author_year, " +
                        " g.genres_name from books b " +
                        " left join authors a on a.id = b.author_id " +
                        " left join genres g on g.id = b.genre_id", mapper);
    }

    @Override
    public void update(String name, Long year, long authorId, long genreId, long id) {
        namedParameterJdbcOperations.update("update books set book_name = :book_name, book_year = :book_year, " +
                        "author_id = :author_id, genre_id = :genre_id " +
                        "where id = :search_id",
                Map.of("book_name", name, "book_year", year, "author_id", authorId,
                        "genre_id", genreId, "search_id", id));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }
}
