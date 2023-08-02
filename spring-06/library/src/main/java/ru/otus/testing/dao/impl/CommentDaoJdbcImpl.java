package ru.otus.testing.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.CommentDao;
import ru.otus.testing.model.Comment;

import java.util.ArrayList;
import java.util.List;
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
    public List<Comment> findByNameAndYear(List<Comment> comments) {
        var list = new ArrayList<Comment>();

        for (var comment : comments) {
            TypedQuery<Comment> query = em.createQuery("select s from Comment s where s.commentText = :commentText ",
                    Comment.class);
            query.setParameter("commentText", comment.getCommentText());
            list.addAll(query.getResultList());
        }

        return list;
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
