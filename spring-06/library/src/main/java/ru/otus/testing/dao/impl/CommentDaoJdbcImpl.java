package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.model.Comment;

import java.util.Optional;

@Repository
public class CommentDaoJdbcImpl implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

    public CommentDaoJdbcImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public Optional<Comment> findByName(String name) {
        return Optional.ofNullable(em.find(Comment.class, name));
    }

    @Override
    public void updateById(long id, Comment comment) {
        Query query = em.createQuery("update Comment s set s.commentText = :commentText where s.id = :id");
        query.setParameter("commentText", comment.getCommentText()).setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Comment s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
