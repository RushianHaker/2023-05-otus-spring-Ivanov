package ru.otus.testing.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import ru.otus.testing.dao.AnswerDao;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;
import ru.otus.testing.service.TestService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class TestServiceImpl implements TestService {
    private final QuestionDao questionDao;

    private final AnswerDao answerDao;

    public TestServiceImpl(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    public HashMap<Question, ArrayList<Answer>> getTestList() {
        var mapQuestions = new HashMap<Question, ArrayList<Answer>>();

        try (var reader = new CSVReader(new InputStreamReader(new ClassPathResource("/test.csv").getInputStream()))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                var listAnswers = new ArrayList<Answer>();

                listAnswers.add(answerDao.createAnswer(nextLine[1]));
                listAnswers.add(answerDao.createAnswer(nextLine[2]));

                mapQuestions.put(questionDao.createQuestion(nextLine[0]), listAnswers);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Csv read error: " + e);
        }

        return mapQuestions;
    }
}
