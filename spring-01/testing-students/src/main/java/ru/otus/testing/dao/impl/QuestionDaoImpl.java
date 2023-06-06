package ru.otus.testing.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private final ClassPathResource classPathResource;

    public QuestionDaoImpl(ClassPathResource classPathResource) {
        this.classPathResource = classPathResource;
    }

    public List<Question> findAll() {
        var listQuestions = new ArrayList<Question>();

        try (var reader = new CSVReader(new InputStreamReader(classPathResource.getInputStream()))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 2) {
                    var listAnswers = new ArrayList<Answer>();

                    for (int i = 1; i < nextLine.length; i++) {
                        if (nextLine[i].contains("correct:")) {
                            listAnswers.add(new Answer(nextLine[i].replaceFirst("correct:", ""), true));
                        } else {
                            listAnswers.add(new Answer(nextLine[i], false));
                        }
                    }

                    listQuestions.add(new Question(nextLine[0], listAnswers));
                }
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Csv read error: " + e);
        }

        return listQuestions;
    }
}
