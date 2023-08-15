package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
    public Author findByNameAndYear(Author author) {
        TypedQuery<Author> query = em.createQuery("select distinct s from Author s where s.name = :name " +
                " and s.year = :year", Author.class);
        query.setParameter("name", author.getName());
        query.setParameter("year", author.getYear());
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }

    @Override
    public void updateById(long id, Author author) {
        var findAuthor = em.find(Author.class, id);
        findAuthor.setName(author.getName());
        findAuthor.setYear(author.getYear());
        em.merge(findAuthor);
    }

    @Override
    public void deleteById(long id) {
        var findAuthor = em.find(Author.class, id);
        em.remove(findAuthor);
    }
}
