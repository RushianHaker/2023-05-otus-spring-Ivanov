package ru.otus.testing.service.impl;


import org.springframework.stereotype.Service;
import ru.otus.testing.service.PrintService;

@Service
public class PrintServiceImpl implements PrintService {
    public void print(String text) {
        System.out.println(text);
    }
}