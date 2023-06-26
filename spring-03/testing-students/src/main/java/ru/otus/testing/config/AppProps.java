package ru.otus.testing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {

    private final Locale locale;

    private final int completeCount;

    private final String pathToTestFile;

    @ConstructorBinding
    public AppProps(Locale locale, int completeCount, String pathToTestFile) {
        this.locale = locale;
        this.completeCount = completeCount;
        this.pathToTestFile = pathToTestFile;
    }

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
