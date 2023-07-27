package ru.otus.testing.dao.impl.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.testing.exception.BookDaoJdbcException;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) {
        try {
            return new Book(resultSet.getLong("id"), resultSet.getString("book_name"),
                    resultSet.getLong("book_year"),
                    new Author(resultSet.getLong("author_id"), resultSet.getString("authors_name"),
                            resultSet.getLong("author_year")),
                    new Genre(resultSet.getLong("genre_id"), resultSet.getString("genres_name")));
        } catch (SQLException e) {
            throw new BookDaoJdbcException("Book dao mapper was throw exception", e);
        }
    }
}
