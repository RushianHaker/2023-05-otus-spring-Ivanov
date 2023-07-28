package ru.otus.testing.dao.impl;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Book;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
public class BookDaoJdbcImpl implements BookDao {
    @PersistenceContext
    private final EntityManager em;

    public BookDaoJdbcImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }


    //todo починить N+1 проблему
    @Override
    public List<Book> findAll() {
        EntityGraph<?> authorsEntityGraph = em.getEntityGraph("otus-student-authors-entity-graph");
        EntityGraph<?> genreEntityGraph = em.getEntityGraph("otus-student-genre-entity-graph");
        EntityGraph<?> commentEntityGraph = em.getEntityGraph("otus-student-comment-entity-graph");
        TypedQuery<Book> query = em.createQuery(
                "select distinct s from Book s " +
                        "left join fetch s.author " +
                        "left join fetch s.comment " +
                        "left join fetch s.genre", Book.class);
        query.setHint(FETCH.getKey(), authorsEntityGraph);
        query.setHint(FETCH.getKey(), genreEntityGraph);
        query.setHint(FETCH.getKey(), commentEntityGraph);
        return query.getResultList();
    }

    //todo починить N+1 проблему
    @Override
    public void updateById(long id, Book book) {
        Query query = em.createQuery(
                "update Book s set s.name = :name, s.year = :year, s.author = :author, " +
                        " s.genre = :genre, s.comment = :comment where s.id = :id");
        query.setParameter("name", book.getName()).setParameter("year", book.getYear())
                .setParameter("author", book.getAuthor()).setParameter("genre", book.getGenre())
                .setParameter("comment", book.getComment()).setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
