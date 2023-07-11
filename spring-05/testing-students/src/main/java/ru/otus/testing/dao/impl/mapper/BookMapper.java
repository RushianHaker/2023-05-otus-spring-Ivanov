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
            return new Book(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getLong("year"),
                    new Author(null, resultSet.getString("author"), null),
                    new Genre(null, resultSet.getString("genre")));
        } catch (SQLException e) {
            throw new BookDaoJdbcException("Book dao mapper was throw exception", e);
        }
    }
}
