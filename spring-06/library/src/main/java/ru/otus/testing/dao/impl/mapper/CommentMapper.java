package ru.otus.testing.dao.impl.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.testing.exception.AuthorDaoJdbcException;
import ru.otus.testing.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int rowNum) {
        try {
            return new Comment(resultSet.getLong("id"), resultSet.getString("comment_text"));
        } catch (SQLException e) {
            throw new AuthorDaoJdbcException("Comment dao mapper was throw exception", e);
        }
    }
}
