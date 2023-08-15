package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.model.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoImpl implements GenreDao {
    @PersistenceContext
    private final EntityManager em;

    public GenreDaoImpl(EntityManager em) {
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
    public Genre findByName(Genre genre) {
            TypedQuery<Genre> query = em.createQuery("select distinct s from Genre s where s.name = :name ",
                    Genre.class);
            query.setParameter("name", genre.getName());
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }

    @Override
    public void updateById(long id, Genre genre) {
        var findGenre = em.find(Genre.class, id);
        findGenre.setName(genre.getName());
        em.merge(findGenre);
    }

    @Override
    public void deleteById(long id) {
        var findGenre = em.find(Genre.class, id);
        em.remove(findGenre);
    }
}
