package ru.otus.testing.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.dao.impl.mapper.GenreMapper;
import ru.otus.testing.model.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbcImpl implements GenreDao {
    private final GenreMapper mapper;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;


    public GenreDaoJdbcImpl(GenreMapper mapper, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.mapper = mapper;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Genre create(Genre genre) {
        var params = new MapSqlParameterSource();
        params.addValue("genres_name", genre.getName());

        var kh = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update("insert into genres (genres_name) values (:genres_name)", params,
                kh, new String[]{"id"});

        genre.setId(kh.getKey().longValue());
        return genre;
    }

    @Override
    public Genre getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, genres_name from genres where id = :id", params, mapper);
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select id, genres_name from genres", mapper);
    }

    @Override
    public void update(Genre genre, long id) {
        namedParameterJdbcOperations.update("update genres set genres_name = :genres_name where id = :search_id",
                Map.of("genres_name", genre.getName(), "search_id", id));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from genres where id = :id", params);
    }
}
