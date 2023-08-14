package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    //todo поправить - https://stackoverflow.com/questions/4334970/hibernate-throws-multiplebagfetchexception-cannot-simultaneously-fetch-multipl
    @Override
    public List<Book> findAll() {
        var booksList = em.createQuery("select distinct s from Book s left join fetch s.author ", Book.class)
                .getResultList();
        var genreList = em.createQuery("select distinct s from Book s left join fetch s.genre", Book.class)
                .getResultList().stream().map(Book::getGenre).toList();
        var commentList = em.createQuery("select distinct s from Book s left join fetch s.comment ", Book.class)
                .getResultList().stream().map(Book::getComment).toList();

        booksList.forEach(book -> {
            book.setGenre();
            book.setComment();
        });

        return queryAuthorFetch;
    }

    //todo поправить, понять почему неп проходит
    @Override
    public void updateById(long id, Book book) {
        var findBook = em.find(Book.class, id);

        findBook.setId(id);
        findBook.setName(book.getName());
        findBook.setYear(book.getYear());
        findBook.setAuthor(book.getAuthor());
        findBook.setGenre(book.getGenre());
        findBook.setComment(book.getComment());

        //todo если поставить persist то все заработает, но почему ?
        em.merge(findBook);
    }

    @Override
    public void deleteById(long id) {
        var findBook = em.find(Book.class, id);
        em.remove(findBook);
    }
}
