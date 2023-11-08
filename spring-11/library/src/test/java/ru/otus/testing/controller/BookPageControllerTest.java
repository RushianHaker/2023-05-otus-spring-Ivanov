package ru.otus.testing.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookPageControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void shouldReturnViewListBooks() {
        webClient.get().uri("/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(
                        body -> body.getResponseBody().equals("booklist")
                );
    }

    @Test
    public void shouldReturnViewInfoBook() {
        webClient.get().uri("/book/infobook/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(
                        body -> body.getResponseBody().equals("infobook")
                );
    }

    @Test
    public void shouldReturnViewCreateBook() {
        webClient.get().uri("/book/addbook")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(
                        body -> body.getResponseBody().equals("addbook")
                );
    }
}
