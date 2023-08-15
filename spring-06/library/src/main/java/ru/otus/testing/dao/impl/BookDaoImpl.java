package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        TypedQuery<Book> query = em.createQuery("select distinct b from Book b", Book.class);
        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public void updateById(long id, Book book) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(FETCH.getKey(), em.getEntityGraph("otus-book-author-genre-entity-graph"));
        var findBook = Optional.ofNullable(em.find(Book.class, id, properties));

        if (findBook.isEmpty()) {
            throw new IllegalArgumentException("Can't find book with id: " + id + "!");
        }

        var presentFindBook = findBook.get();
        presentFindBook.setName(book.getName());
        presentFindBook.setYear(book.getYear());
        presentFindBook.setAuthor(book.getAuthor());
        presentFindBook.setGenre(book.getGenre());
        presentFindBook.setComment(book.getComment());

        em.merge(book);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(FETCH.getKey(), em.getEntityGraph("otus-book-author-genre-entity-graph"));
        var findBook = Optional.ofNullable(em.find(Book.class, id, properties));

        if (findBook.isEmpty()) {
            throw new IllegalArgumentException("Can't find book with id: " + id + "!");
        }

        em.remove(findBook.get());
    }
}
