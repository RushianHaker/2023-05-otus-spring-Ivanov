package ru.otus.testing.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.exception.QuestionDaoException;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;
import ru.otus.testing.service.MessageSourceService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    private final MessageSourceService messageSourceService;

    private final ClassPathResource classPathResource;

    public QuestionDaoImpl(MessageSourceService messageSourceService,
                           @Value("${application.pathToTestFile}") String path) {
        this.messageSourceService = messageSourceService;
        this.classPathResource = new ClassPathResource(path);
    }

    private static void addAnswersInList(List<Answer> listAnswers, String answer) {
        if (answer.contains("correct:")) {
            listAnswers.add(new Answer(answer.replaceFirst("correct:", ""), true));
        } else {
            listAnswers.add(new Answer(answer, false));
        }
    }

    public List<Question> findAll() {
        var listQuestions = new ArrayList<Question>();

        try (var reader = new CSVReader(new InputStreamReader(classPathResource.getInputStream()))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                listQuestions.add(new Question(messageSourceService.getMessage(nextLine[0], null),
                        findAnswers(nextLine)));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new QuestionDaoException("Array was throw exception: ", e);
        } catch (CsvValidationException e) {
            throw new QuestionDaoException("User-defined csv validator fails: ", e);
        } catch (IOException e) {
            throw new QuestionDaoException("Reads resource or next line from the buffer was throw exception: ", e);
        }

        return listQuestions;
    }

    private List<Answer> findAnswers(String[] nextLine) {
        var listAnswers = new ArrayList<Answer>();

        for (int i = 1; i < nextLine.length; i++) {
            String message = messageSourceService.getMessage(nextLine[i], null);
            String[] argSplitAnswers = message.split(",");

            if (argSplitAnswers.length == 0) {
                return listAnswers;
            } else {
                for (String answer : argSplitAnswers) {
                    addAnswersInList(listAnswers, answer);
                }
            }
        }

        return listAnswers;
    }
}
