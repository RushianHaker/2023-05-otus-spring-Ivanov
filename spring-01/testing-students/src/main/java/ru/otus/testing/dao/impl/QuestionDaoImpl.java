package ru.otus.testing.dao.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.exception.QuestionDaoImplException;
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
                listQuestions.add(new Question(nextLine[0], findAnswers(nextLine)));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new QuestionDaoImplException("Array was throw exception: ", e);
        } catch (CsvValidationException e) {
            throw new QuestionDaoImplException("User-defined csv validator fails: ", e);
        } catch (IOException e) {
            throw new QuestionDaoImplException("Reads resource or next line from the buffer was throw exception: ", e);
        }

        return listQuestions;
    }

    private List<Answer> findAnswers(String[] nextLine) {
        var listAnswers = new ArrayList<Answer>();
        for (int i = 1; i < nextLine.length; i++) {
            if (nextLine[i].contains("correct:")) {
                listAnswers.add(new Answer(nextLine[i].replaceFirst("correct:", ""), true));
            } else {
                listAnswers.add(new Answer(nextLine[i], false));
            }
        }
        return listAnswers;
    }
}
