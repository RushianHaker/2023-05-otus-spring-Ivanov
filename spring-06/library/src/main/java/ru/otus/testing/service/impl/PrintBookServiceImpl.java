package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.model.Book;
import ru.otus.testing.model.Comment;
import ru.otus.testing.service.PrintBookService;

import java.util.List;

@Service
public class PrintBookServiceImpl implements PrintBookService {

    @Override
    public String printBookToConsole(Book presentedBookInfo) {
        return "Book info: " +
                " id: " + presentedBookInfo.getId() +
                ", name: " + presentedBookInfo.getName() +
                ", year: " + presentedBookInfo.getYear() +
                ", author's name: " + presentedBookInfo.getAuthor().getName() +
                ", genre: " + presentedBookInfo.getGenre().getName() +
                ", comments: " + presentedBookInfo.getComments().stream().map(Comment::getCommentText).toList();
    }

    @Override
    public String printListBooksToConsole(List<Book> booksList) {
        var stringBuilder = new StringBuilder("Books info list (size: " + booksList.size() + "): ");

        for (var bookInfo : booksList) {
            stringBuilder
                    .append("Book-").append(bookInfo.getId()).append(")")
                    .append(" id: ").append(bookInfo.getId())
                    .append(", name: ").append(bookInfo.getName())
                    .append(", year: ").append(bookInfo.getYear())
                    .append(", author : ").append(bookInfo.getAuthor().getName())
                    .append(", genre: ").append(bookInfo.getGenre().getName())
                    .append(", comments: ").append(bookInfo.getComments().stream().map(Comment::getCommentText).toList());
        }

        return stringBuilder.toString();
    }
}
