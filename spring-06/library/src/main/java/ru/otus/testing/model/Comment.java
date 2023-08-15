package ru.otus.testing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public Comment(long id, String commentText) {
        this.id = id;
        this.commentText = commentText;
    }

    public Comment() {

    }
}
