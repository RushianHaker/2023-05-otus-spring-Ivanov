package ru.otus.testing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.testing.service.ChatService;

@Slf4j
@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(LibraryApplication.class, args);

        ChatService chatService = ctx.getBean(ChatService.class);
        chatService.generateMessagesLoop();
    }
}
