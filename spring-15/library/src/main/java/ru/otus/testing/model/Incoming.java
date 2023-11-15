package ru.otus.testing.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Incoming {
    private String sender;

    private String recipient;

    private String message;
}
