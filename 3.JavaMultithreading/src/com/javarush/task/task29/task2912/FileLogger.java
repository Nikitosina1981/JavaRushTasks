package com.javarush.task.task29.task2912;

public class FileLogger extends AbstractLogger {
    public FileLogger(int level) {
        this.level = level;
        this.type1 = "Logging to file: ";
    }
}