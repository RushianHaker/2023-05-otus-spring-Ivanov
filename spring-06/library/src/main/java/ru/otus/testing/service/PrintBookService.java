package ru.otus.testing.service;


import ru.otus.testing.model.Book;

import java.util.List;

public interface PrintBookService {
    String printBookToConsole(Book presentedBookInfo);

    String printListBooksToConsole(List<Book> booksList);
}
