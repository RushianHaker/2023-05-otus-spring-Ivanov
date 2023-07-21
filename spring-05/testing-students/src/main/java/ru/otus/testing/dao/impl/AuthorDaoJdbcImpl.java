package ru.otus.testing.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.dao.impl.mapper.AuthorMapper;
import ru.otus.testing.model.Author;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbcImpl implements AuthorDao {
    private final AuthorMapper mapper;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbcImpl(AuthorMapper mapper, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.mapper = mapper;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Author create(Author author) {
        var params = new MapSqlParameterSource();
        params.addValue("authors_name", author.getName());
        params.addValue("author_year", author.getYear());

        var kh = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update("insert into authors (authors_name, author_year) " +
                        "values (:authors_name, :author_year)",
                params, kh, new String[]{"id"});

        author.setId(kh.getKey().longValue());
        return author;
    }

    @Override
    public Author getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, authors_name, author_year from authors where id = :id", params, mapper);
    }

    @Override
    public Author getByName(String name) {
        Map<String, String> params = Collections.singletonMap("name", name);
        var authorList = namedParameterJdbcOperations.query(
                "select id, authors_name, author_year from authors where authors_name = :name", params, mapper);
        return authorList.isEmpty() ? null : authorList.get(0);
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select id, authors_name, author_year from authors", mapper);
    }

    @Override
    public void update(Author author, long id) {
        namedParameterJdbcOperations.update(
                "update authors set authors_name = :authors_name, author_year = :author_year where id = :search_id",
                Map.of("authors_name", author.getName(), "author_year", author.getYear(),
                        "search_id", id));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from authors where id = :id", params);
    }
}
