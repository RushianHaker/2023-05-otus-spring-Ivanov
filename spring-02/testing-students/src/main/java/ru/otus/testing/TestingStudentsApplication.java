package ru.otus.testing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.testing.config.ApplicationConfig;
import ru.otus.testing.service.TestService;

@ComponentScan("ru.otus.testing")
public class TestingStudentsApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        TestService service = context.getBean(TestService.class);
        service.testing();
    }
}
