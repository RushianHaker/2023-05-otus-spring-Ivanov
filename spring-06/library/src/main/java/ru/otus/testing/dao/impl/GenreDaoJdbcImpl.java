package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.model.Genre;

import java.util.Optional;

@Repository
public class GenreDaoJdbcImpl implements GenreDao {
    @PersistenceContext
    private final EntityManager em;

    public GenreDaoJdbcImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> findByName(String name) {
        return Optional.ofNullable(em.find(Genre.class, name));
    }

    @Override
    public void updateById(long id, Genre genre) {
        Query query = em.createQuery("update Genre s set s.name = :name where s.id = :id");
        query.setParameter("name", genre.getName()).setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Genre s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
