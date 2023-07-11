package ru.otus.testing.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.testing.dao.BookDao;
import ru.otus.testing.service.BookService;
import ru.otus.testing.service.IOService;



@SpringBootTest
class TestServiceImplBook {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private IOService ioService;
    @Autowired
    private BookService service;
    @Autowired
    private UserAnswerServiceImpl userAnswerService;

    @Test
    void printTest() {
        //todo
     /*   when(bookDao.findAll()).thenReturn(List.of(question));
        when(messageSourceService.getMessage("print_question", new String[]{question.getQuestion()}))
                .thenReturn(question.getQuestion());

        service.testing();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(ioService, times(4)).outputString(captor.capture());

        String actualOutput = captor.getAllValues().stream()
                .collect(Collectors.joining(System.lineSeparator()));
        assertTrue(actualOutput.contains(question.getQuestion()));
        assertTrue(actualOutput.contains(answer1.getAnswer()));
        assertTrue(actualOutput.contains(answer2.getAnswer()));*/
    }
}
