package ru.otus.testing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.testing.service.TestService;

@ComponentScan("ru.otus.testing")
@PropertySource("classpath:application.properties")
@Configuration
public class TestingStudentsApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestingStudentsApplication.class);
        TestService service = context.getBean(TestService.class);
        service.testing();
    }
}
