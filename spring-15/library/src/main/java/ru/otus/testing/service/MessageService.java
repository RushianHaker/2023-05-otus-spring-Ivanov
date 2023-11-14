package ru.otus.testing.service;

import ru.otus.testing.model.Incoming;
import ru.otus.testing.model.Outgoing;

import java.util.List;

public interface MessageService {
    List<Incoming> sending(Outgoing outgoing);
}
