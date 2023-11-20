package ru.otus.testing.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.testing.model.Book;
import ru.otus.testing.service.BookService;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "menu";
    }

    @HystrixCommand(commandKey = "waitPage", fallbackMethod = "callWaitPage")
    @GetMapping("/book")
    public String getLibrary(@RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size, Model model) {
        randomSleep();

        log.info("Get all books in library");

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);

        Page<Book> books = bookService.findPaginated(PageRequest.of(currentPage, pageSize));
        model.addAttribute("books", books);

        return "booklist";
    }

    public String callWaitPage(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                               Model model) {
        log.warn("fallBackMethod");
        return "waitPage";
    }

    private void randomSleep() {
        if (new Random().nextInt(5) > 3) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
