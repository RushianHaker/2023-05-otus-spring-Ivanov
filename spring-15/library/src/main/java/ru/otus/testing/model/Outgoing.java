package ru.otus.testing.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class Outgoing {
    private String sender;

    private List<String> recipients;

    private String message;
}
