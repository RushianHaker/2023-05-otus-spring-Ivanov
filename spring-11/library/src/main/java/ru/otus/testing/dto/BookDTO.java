package ru.otus.testing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.testing.model.Book;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class BookDTO {
    private String id;
    private String title;
    private String author;
    private String genre;

    public Book dtoToBook() {
        return Book.builder().title(title)
                .author(author)
                .genre(genre)
                .build();
    }

    public static BookDTO toDto(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre());
    }
}
