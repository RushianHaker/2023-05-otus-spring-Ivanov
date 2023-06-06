package ru.otus.testing.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.testing.service.TestService;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestServiceImplTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
    TestService service = context.getBean(TestService.class);

    @DisplayName("Single test successful")
    @Test
    void getTestListTest() {
        var listQuesting = service.getTest();

        assertEquals(5, listQuesting.size());
        assertEquals(2, listQuesting.get(0).getAnswer().size());

        assertEquals("That is your name?", listQuesting.get(0).getQuestion());

        assertEquals("Max", listQuesting.get(0).getAnswer().get(0).getAnswer());
        assertEquals(true, listQuesting.get(0).getAnswer().get(0).isCorrect());

        assertEquals("Anna", listQuesting.get(0).getAnswer().get(1).getAnswer());
        assertEquals(false, listQuesting.get(0).getAnswer().get(1).isCorrect());
    }
}
