package com.javarush.task.task29.task2912;

public class SmsLogger extends AbstractLogger {

    public SmsLogger(int level) {
        this.level = level;
        this.type1 = "Send sms to CEO: ";
    }
}