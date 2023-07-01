package ru.otus.testing.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.testing.service.MessageSourceService;

import java.util.Locale;

@Service
public class MessageSourceServiceImpl implements MessageSourceService {

    private final Locale locale;

    private final MessageSource messageSource;


    public MessageSourceServiceImpl(@Value("${application.locale}") Locale locale, MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }


    @Override
    public String getMessage(String message, String[] args) {
        return messageSource.getMessage(message, args, locale);
    }
}
