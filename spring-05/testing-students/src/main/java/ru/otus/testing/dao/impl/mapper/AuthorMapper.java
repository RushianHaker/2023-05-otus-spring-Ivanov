package ru.otus.testing.dao.impl.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.testing.exception.AuthorDaoJdbcException;
import ru.otus.testing.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) {
        try {
            return new Author(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getInt("year"));
        } catch (SQLException e) {
            throw new AuthorDaoJdbcException("Author dao mapper was throw exception", e);
        }
    }
}
