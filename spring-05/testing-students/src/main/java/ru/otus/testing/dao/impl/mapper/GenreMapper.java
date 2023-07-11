package ru.otus.testing.dao.impl.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.testing.exception.GenreDaoJdbcException;
import ru.otus.testing.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int rowNum) {
        try {
            return new Genre(resultSet.getLong("id"), resultSet.getString("name"));
        } catch (SQLException e) {
            throw new GenreDaoJdbcException("Genre dao mapper was throw exception", e);
        }
    }
}
