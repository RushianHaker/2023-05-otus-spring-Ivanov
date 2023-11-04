package ru.otus.testing.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private long id;

    private String commentText;

    private BookDTO book;

    public CommentDTO(long id, String commentText, BookDTO book) {
        this.id = id;
        this.commentText = commentText;
        this.book = book;
    }

    public CommentDTO(long id, String commentText) {
        this.id = id;
        this.commentText = commentText;
    }

    public CommentDTO(long id) {
        this.id = id;
    }
}
