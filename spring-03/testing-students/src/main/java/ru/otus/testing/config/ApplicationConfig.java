package ru.otus.testing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class ApplicationConfig {

    @Value("${application.locale}")
    private Locale locale;

    @Value("${application.completeCount}")
    private int completeCount;

    @Value("${application.pathToTestFile}")
    private String pathToTestFile;

    public Locale getLocale() {
        return locale;
    }

    public int getCompleteCount() {
        return completeCount;
    }

    public String getPathToTestFile() {
        return pathToTestFile;
    }
}
