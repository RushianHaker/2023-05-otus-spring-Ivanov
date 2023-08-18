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
    private long id;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment(long id, String commentText, Book book) {
        this.id = id;
        this.commentText = commentText;
        this.book = book;
    }

    public Comment(String commentText, Book book) {
        this.commentText = commentText;
        this.book = book;
    }

    public Comment() {

    }
}
