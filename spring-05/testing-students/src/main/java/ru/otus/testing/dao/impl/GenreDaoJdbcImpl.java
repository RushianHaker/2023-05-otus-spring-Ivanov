package ru.otus.testing.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.dao.impl.mapper.GenreMapper;
import ru.otus.testing.model.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbcImpl implements GenreDao {
    private final static GenreMapper GENRE_MAPPER = new GenreMapper();
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;


    public GenreDaoJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void create(Genre genre) {
        namedParameterJdbcOperations.update("insert into genres (id, \"name\") values (:id, :name)",
                Map.of("id", genre.getId(), "name", genre.getName()));
    }

    @Override
    public Genre getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, \"name\" from genres where id = :id", params, GENRE_MAPPER);
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select id, \"name\" from genres", GENRE_MAPPER);
    }

    @Override
    public void update(Genre genre, long id) {
        namedParameterJdbcOperations.update("update genres set id = :id, \"name\" = :name where id = :search_id",
                Map.of("id", id, "name", genre.getName(), "search_id", id));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from genres where id = :id", params);
    }
}
