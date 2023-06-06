package ru.otus.testing.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import ru.otus.testing.dao.AnswerDao;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private final AnswerDao answerDao;
    private final ClassPathResource classPathResource;

    public QuestionDaoImpl(AnswerDao answerDao, ClassPathResource classPathResource) {
        this.answerDao = answerDao;
        this.classPathResource = classPathResource;
    }

    public Question createQuestion(String name, List<Answer> answerList) {
        return new Question(name, answerList);
    }

    public List<Question> findAll() {
        var listQuestions = new ArrayList<Question>();

        try (var reader = new CSVReader(new InputStreamReader(classPathResource.getInputStream()))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 2) {
                    var listAnswers = new ArrayList<Answer>();

                    for (int i = 1; i < nextLine.length; i++) {
                        listAnswers.add(answerDao.createAnswer(nextLine[i]));
                    }

                    listQuestions.add(createQuestion(nextLine[0], listAnswers));
                }
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Csv read error: " + e);
        }

        return listQuestions;
    }
}
