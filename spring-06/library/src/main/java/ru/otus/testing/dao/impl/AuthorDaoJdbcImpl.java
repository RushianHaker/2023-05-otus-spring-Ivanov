package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.model.Author;

import java.util.Optional;

@Repository
public class AuthorDaoJdbcImpl implements AuthorDao {
    @PersistenceContext
    private final EntityManager em;

    public AuthorDaoJdbcImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Optional<Author> findByName(String name) {
        return Optional.ofNullable(em.find(Author.class, name));
    }

    @Override
    public void updateById(long id, Author author) {
        Query query = em.createQuery("update Author s set s.name = :name where s.id = :id");
        query.setParameter("name", author.getName()).setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Author s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
