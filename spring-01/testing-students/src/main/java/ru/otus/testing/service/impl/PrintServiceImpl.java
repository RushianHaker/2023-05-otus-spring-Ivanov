package ru.otus.testing.service.impl;


import ru.otus.testing.service.PrintService;

public class PrintServiceImpl implements PrintService {
    public void print(String text) {
        System.out.println(text);
    }
}