package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.service.ConvertModelInfoToStringService;

import java.util.List;

@Service
public class ConvertModelInfoToStringServiceImpl implements ConvertModelInfoToStringService {

    @Override
    public String convertBookInfoToString(Book presentedBookInfo) {
        return "Book info: " +
                " id: " + presentedBookInfo.getId() +
                ", name: " + presentedBookInfo.getName() +
                ", year: " + presentedBookInfo.getYear() +
                ", author's name: " + presentedBookInfo.getAuthor().getName() +
                ", author's year: " + presentedBookInfo.getAuthor().getYear() +
                ", genre: " + presentedBookInfo.getGenre().getName() +
                ", comments: " + presentedBookInfo.getComments().stream().map(Comment::getCommentText).toList();
    }

    @Override
    public String convertListBooksInfoToString(List<Book> booksList) {
        var stringBuilder = new StringBuilder("Books info list (size: " + booksList.size() + "): ");

        for (var bookInfo : booksList) {
            stringBuilder
                    .append("\n")
                    .append("Book-").append(bookInfo.getId()).append(")")
                    .append(" id: ").append(bookInfo.getId())
                    .append(", name: ").append(bookInfo.getName())
                    .append(", year: ").append(bookInfo.getYear())
                    .append(", author's name: ").append(bookInfo.getAuthor().getName())
                    .append(", author's year: ").append(bookInfo.getAuthor().getYear())
                    .append(", genre: ").append(bookInfo.getGenre().getName());
        }

        return stringBuilder.toString();
    }
}
