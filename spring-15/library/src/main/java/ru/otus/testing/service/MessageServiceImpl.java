package ru.otus.testing.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.testing.model.Incoming;
import ru.otus.testing.model.Outgoing;

import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Override
    public List<Incoming> sending(Outgoing outgoing) {
        log.info("Outgoing {}", outgoing);

        List<Incoming> in = outgoing.getRecipients().stream()
                .map(recipient -> Incoming.builder().recipient(recipient)
                        .sender(outgoing.getSender()).message(outgoing.getMessage()).build())
                .toList();

        in.forEach(incoming -> log.info("Incoming {}", incoming));
        delay();
        return in;
    }

    private static void delay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
