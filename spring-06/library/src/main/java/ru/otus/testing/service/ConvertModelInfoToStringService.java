package ru.otus.testing.service;


import ru.otus.testing.dto.BookDTO;
import ru.otus.testing.model.Book;

import java.util.List;

public interface ConvertModelInfoToStringService {
    String convertBookInfoToString(BookDTO presentedBookInfo);

    String convertListBooksInfoToString(List<Book> booksList);
}
