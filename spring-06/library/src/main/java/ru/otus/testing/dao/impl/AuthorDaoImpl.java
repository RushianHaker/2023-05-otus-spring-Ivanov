package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.AuthorDao;
import ru.otus.testing.model.Author;

import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    @PersistenceContext
    private final EntityManager em;

    public AuthorDaoImpl(EntityManager em) {
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
    public Author findByNameAndYear(String name, long year) {
        TypedQuery<Author> query = em.createQuery("select s from Author s where s.name = :name " +
                " and s.year = :year", Author.class);
        query.setParameter("name", name);
        query.setParameter("year", year);
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }

    @Override
    public void deleteById(long id) {
        var findAuthor = findById(id);
        findAuthor.ifPresent(em::remove);
    }
}
