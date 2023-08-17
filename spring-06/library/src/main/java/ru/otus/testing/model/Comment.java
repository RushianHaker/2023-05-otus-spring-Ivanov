package ru.otus.testing.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
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
