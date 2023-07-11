package ru.otus.testing.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.dao.impl.mapper.AuthorMapper;
import ru.otus.testing.model.Author;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbcImpl implements AuthorDao {
    private final static AuthorMapper AUTHOR_MAPPER = new AuthorMapper();
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void create(Author book) {
        namedParameterJdbcOperations.update("insert into authors (id, name, year) values (:id, :name, :year)",
                Map.of("id", book.id(), "name", book.name(), "year", book.year()));
    }

    @Override
    public Author getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, year from authors where id = :id", params, AUTHOR_MAPPER);
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select id, name, year from authors", AUTHOR_MAPPER);
    }

    @Override
    public void update(Author book) {
        namedParameterJdbcOperations.update("update authors set id = :id, name = :name, year = :year",
                Map.of("id", book.id(), "name", book.name(), "year", book.year()));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from authors where id = :id", params);
    }
}
