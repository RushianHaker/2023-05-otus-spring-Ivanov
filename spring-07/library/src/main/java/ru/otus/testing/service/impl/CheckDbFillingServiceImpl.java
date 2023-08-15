package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.dao.AuthorRepository;
import ru.otus.testing.dao.CommentRepository;
import ru.otus.testing.dao.GenreRepository;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.CheckDbFillingService;

import java.util.List;

@Service
public class CheckDbFillingServiceImpl implements CheckDbFillingService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;


    public CheckDbFillingServiceImpl(AuthorRepository authorRepository, GenreRepository genreRepository,
                                     CommentRepository commentRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * Проверяет наличие автора в БД и при его отсутствии - создает
     *
     * @param author - автор книги
     * @return возвращает модель автора - либо из БД, либо созданную и сохраненную
     */
    @Override
    public Author findOrCreateAuthor(Author author) {
        var authorInfoFromDb = authorRepository.findByNameAndYear(author.getName(), author.getYear());
        if (authorInfoFromDb == null) {
            authorInfoFromDb = authorRepository.save(author);
        }
        return authorInfoFromDb;
    }

    /**
     * Проверяет наличие жанра в БД и при его отсутствии - создает
     *
     * @param genre - жанр книги
     * @return возвращает модель жанра - либо из БД, либо созданный и сохраненный
     */
    @Override
    public Genre findOrCreateGenre(Genre genre) {
        var genreInfoFromDb = genreRepository.findByName(genre.getName());
        if (genreInfoFromDb == null) {
            genreInfoFromDb = genreRepository.save(genre);
        }
        return genreInfoFromDb;
    }

    /**
     * Проверяет наличие комментариев в БД и при их отсутствии - создает
     *
     * @param commentsList - комментарии у книги
     * @return возвращает лист комментариев - либо из БД, либо созданный и сохраненный
     */
    @Override
    public List<Comment> findOrCreateComments(List<Comment> commentsList) {
        var commentsInfoFromDbList = commentRepository.findByCommentsTextList(commentsList.stream()
                .map(Comment::getCommentText).toList());
        if (commentsInfoFromDbList.isEmpty()) {
            for (var comment : commentsList) {
                commentsInfoFromDbList.add(commentRepository.save(comment));
            }
        }

        return commentsInfoFromDbList;
    }
}
