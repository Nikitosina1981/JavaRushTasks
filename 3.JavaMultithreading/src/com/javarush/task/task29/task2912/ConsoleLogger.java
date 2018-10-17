package com.javarush.task.task29.task2912;

public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level) {
        this.level = level;
        this.type1 = "Logging to console: ";
    }
}