package ru.otus.testing.service;


import ru.otus.testing.model.Author;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;

import java.util.List;

public interface UserAnswerService {
    long checkUserAnswerToLong(String msg);

    Author getAuthorInfo();

    Genre getGenreInfo();

    List<Comment> getListCommentInfo();
}
