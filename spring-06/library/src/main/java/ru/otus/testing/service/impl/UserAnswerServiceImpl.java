package ru.otus.testing.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.testing.model.Author;
import ru.otus.testing.model.Comment;
import ru.otus.testing.model.Genre;
import ru.otus.testing.service.IOService;
import ru.otus.testing.service.UserAnswerService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    private final IOService ioService;

    public UserAnswerServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public long checkUserAnswerToLong(String msg) {
        boolean correct = false;
        long userAnswer = 0;

        while (!correct) {
            try {
                userAnswer = ioService.readLongWithPrompt(msg);
                correct = true;
            } catch (InputMismatchException e) {
                ioService.readNextWithPrompt("Don't supported symbol, please try again");
            }
        }
        return userAnswer;
    }

    /**
     * Получение Автора
     */
    @Override
    public Author getAuthorInfo() {
        var name = ioService.readNextWithPrompt("- Enter author name: ");
        var year = checkUserAnswerToLong("- Enter author years: ");
        return new Author(name, year);
    }

    /**
     * Получение Жанра
     */
    @Override
    public Genre getGenreInfo() {
        var name = ioService.readNextWithPrompt("- Enter books genre name: ");
        return new Genre(name);
    }

    /**
     * Получение списка Комментариев в цикле, который работает до тех пор, пока пользователь сам не остановит
     */
    @Override
    public List<Comment> getListCommentInfo() {
        var listModelsInfo = new ArrayList<Comment>();

        boolean stop = false;
        String stopWord = "no";

        while (!stop) {
            var name = ioService.readNextWithPrompt("- Enter books comment: ");
            listModelsInfo.add(new Comment(name));

            var userStopWord = ioService.readNextWithPrompt("- Do you want to enter yet comments? (yes/no): ");

            if (stopWord.equals(userStopWord)) {
                stop = true;
            }
        }
        return listModelsInfo;
    }
}
