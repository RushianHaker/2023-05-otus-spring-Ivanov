package ru.otus.testing.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.testing.model.Outgoing;

import java.util.Collection;

@MessagingGateway
public interface MessageGateway {
    @Gateway(requestChannel = "messageChannel")
    void process(Collection<Outgoing> outgoings);
}
