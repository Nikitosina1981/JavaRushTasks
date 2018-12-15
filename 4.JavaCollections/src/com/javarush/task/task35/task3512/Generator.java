package com.javarush.task.task35.task3512;

public class Generator<T> {
    Class<T> newClass;

    public Generator(Class<T> newClass)
    {
        this.newClass = newClass;
    }

    T newInstance() throws IllegalAccessException, InstantiationException
    {
        return newClass.newInstance();
    }
}
