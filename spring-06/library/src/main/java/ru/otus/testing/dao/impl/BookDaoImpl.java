package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.model.Book;

import java.util.List;
import java.util.Optional;

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
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery(
                "select distinct s from Book s " +
                        "left join fetch s.author " +
                        "left join fetch s.comment " +
                        "left join fetch s.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public void updateById(long id, Book book) {
        var findBook = em.find(Book.class, id);

        findBook.setName(book.getName());
        findBook.setYear(book.getYear());
        findBook.setAuthor(book.getAuthor());
        findBook.setGenre(book.getGenre());
        findBook.setComment(book.getComment());

        em.merge(findBook);
    }

    @Override
    public void deleteById(long id) {
        var findBook = em.find(Book.class, id);
        em.remove(findBook);
    }
}
