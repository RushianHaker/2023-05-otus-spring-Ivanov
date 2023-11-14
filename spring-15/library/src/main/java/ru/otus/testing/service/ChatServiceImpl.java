package ru.otus.testing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.testing.model.Outgoing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private static final List<Outgoing> OUTGOINGS = List.of(
            Outgoing.builder()
                    .sender("HomerSimpson")
                    .recipients(List.of("MargeSimpson", "BartSimpson", "LisaSimpson", "MeggeSimpson"))
                    .message("I eat some junk food right now!")
                    .build(),
            Outgoing.builder()
                    .sender("MargeSimpson")
                    .recipients(List.of("HomerSimpson", "BartSimpson", "LisaSimpson", "MeggeSimpson"))
                    .message("I'm so tired!")
                    .build(),
            Outgoing.builder()
                    .sender("BartSimpson")
                    .recipients(List.of("MargeSimpson", "HomerSimpson", "LisaSimpson"))
                    .message("Oh karamba, eat my shorts")
                    .build(),
            Outgoing.builder()
                    .sender("LisaSimpson")
                    .recipients(List.of("BartSimpson"))
                    .message("Turn-off you music, i want to learn english!")
                    .build(),
            Outgoing.builder()
                    .sender("MeggeSimpson")
                    .recipients(List.of("MargeSimpson", "HomerSimpson"))
                    .message("I want sleep!")
                    .build()
    );

    private final MessageGateway messageGateway;

    @Override
    public void generateMessagesLoop() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        for (int i = 0; i < 10; i++) {
            int id = i + 1;

            pool.execute(() -> {
                Collection<Outgoing> items = generateOutgoings();
                log.info("{}, New message: {}", id, items.stream()
                        .map(Outgoing::toString).collect(Collectors.joining("\n")));

                messageGateway.process(items);
            });

            delay();
        }
    }

    private static Outgoing generateOutgoing() {
        return OUTGOINGS.get(RandomUtils.nextInt(0, OUTGOINGS.size()));
    }

    private static Collection<Outgoing> generateOutgoings() {
        List<Outgoing> items = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 5); i++) {
            items.add(generateOutgoing());
        }

        return items;
    }

    private void delay() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
