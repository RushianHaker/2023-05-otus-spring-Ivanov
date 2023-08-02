package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.GenreDao;
import ru.otus.testing.model.Genre;

import java.util.ArrayList;
import java.util.List;
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
    public List<Genre> findByNameAndYear(List<Genre> genres) {
        var list = new ArrayList<Genre>();

        for (var genre : genres) {
            TypedQuery<Genre> query = em.createQuery("select s from Genre s where s.name = :name ", Genre.class);
            query.setParameter("name", genre.getName());
            list.addAll(query.getResultList());
        }

        return list;
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
