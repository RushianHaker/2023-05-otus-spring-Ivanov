package ru.otus.testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.testing.service.TestService;
import ru.otus.testing.utils.PrintService;

public class TestingStudentsApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestService service = context.getBean(TestService.class);
        var listQuestions = service.getTest();

        for (var question : listQuestions) {
            PrintService.print("\n" + "Question: " + question.getQuestion());
            PrintService.print("Answers:");
            for (var answer : question.getAnswer()) {
                PrintService.print("* " + answer.getAnswer());
            }
        }

        context.close();
    }
}
