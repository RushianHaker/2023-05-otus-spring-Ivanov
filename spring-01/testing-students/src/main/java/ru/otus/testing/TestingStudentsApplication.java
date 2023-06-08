package ru.otus.testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.testing.service.TestService;

public class TestingStudentsApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestService service = context.getBean(TestService.class);
        service.printTest();
        context.close();
    }
}
