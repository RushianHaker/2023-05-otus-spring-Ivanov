package ru.otus.testing.service;


import ru.otus.testing.model.Author;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

import java.util.List;

public interface CheckDbFillingService {

    Author findOrCreateAuthor(Author author);

    Genre findOrCreateGenre(Genre genre);

    List<Comment> findOrCreateComments(List<Comment> commentsList);

}
