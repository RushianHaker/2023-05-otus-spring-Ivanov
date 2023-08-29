package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;

import java.util.*;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;


@Repository
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    private final EntityManager em;

    public BookDaoImpl(EntityManager em) {
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
        Map<String, Object> properties = new HashMap<>();
        properties.put(FETCH.getKey(), em.getEntityGraph("otus-book-author-genre-entity-graph"));
        return Optional.ofNullable(em.find(Book.class, id, properties));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("otus-book-author-genre-entity-graph");
        TypedQuery<Book> query = em.createQuery("select distinct b from Book b join fetch b.comments", Book.class);
        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public void updateById(Book book) {
        var findBook = findById(book.getId());

        if (findBook.isPresent()) {
            var presentBook = findBook.get();
            presentBook.setName(book.getName());
            presentBook.setYear(book.getYear());
            presentBook.setAuthor(book.getAuthor());
            presentBook.setGenre(book.getGenre());

            em.merge(presentBook);
        }
    }

    @Override
    public void deleteById(long id) {
        var findBook = findById(id);
        findBook.ifPresent(em::remove);
    }
}
