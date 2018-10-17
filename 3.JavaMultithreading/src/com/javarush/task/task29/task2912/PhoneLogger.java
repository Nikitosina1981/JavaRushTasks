package com.javarush.task.task29.task2912;

public class PhoneLogger extends AbstractLogger
{

    public PhoneLogger(int level)
    {
        this.level = level;
        this.type1 = "Call to director: ";
    }
}