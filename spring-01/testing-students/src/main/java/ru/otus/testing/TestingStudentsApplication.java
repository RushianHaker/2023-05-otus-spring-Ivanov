package ru.otus.testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.testing.service.TestService;
import ru.otus.testing.utils.PrintUtil;

public class TestingStudentsApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestService service = context.getBean(TestService.class);
        var mapQuestions = service.getTestList();

        for (var question : mapQuestions.keySet()) {
            PrintUtil.print("\n" + "Question: " + question.getQuestion());
            PrintUtil.print("Answers:");
            for (var answer : mapQuestions.get(question)) {
                PrintUtil.print("* " + answer.getAnswer());
            }
        }

        context.close();
    }
}
