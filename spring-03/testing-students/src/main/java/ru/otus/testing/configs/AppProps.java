package ru.otus.testing.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {

    private final String message;
    private final Locale locale;

    @ConstructorBinding
    public AppProps(String message, Locale locale) {
        this.message = message;
        this.locale = locale;
    }

    public String getMessage() {
        return message;
    }


    public Locale getLocale() {
        return locale;
    }
}
