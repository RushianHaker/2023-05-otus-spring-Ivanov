package ru.otus.testing.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import ru.otus.testing.dao.impl.QuestionDaoImpl;
import ru.otus.testing.service.impl.PrintServiceImpl;
import ru.otus.testing.service.impl.TestResultServiceImpl;
import ru.otus.testing.service.impl.TestServiceImpl;
import ru.otus.testing.service.impl.UserServiceImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Bean
    public ClassPathResource classResource(@Value("${application.pathToTestFile}") String pathToTestFile) {
        return new ClassPathResource(pathToTestFile);
    }

    @Bean
    public QuestionDaoImpl questionDao(ClassPathResource classResource) {
        return new QuestionDaoImpl(classResource);
    }

    @Bean
    public PrintServiceImpl printService() {
        return new PrintServiceImpl();
    }

    @Bean
    public UserServiceImpl userService(PrintServiceImpl printService) {
        return new UserServiceImpl(printService);
    }

    @Bean
    public TestResultServiceImpl testResultService() {
        return new TestResultServiceImpl();
    }

    @Bean
    public TestServiceImpl testService(QuestionDaoImpl questionDao, PrintServiceImpl printService,
                                       UserServiceImpl userService, TestResultServiceImpl testResultService) {
        return new TestServiceImpl(questionDao, printService, userService, testResultService);
    }
}
