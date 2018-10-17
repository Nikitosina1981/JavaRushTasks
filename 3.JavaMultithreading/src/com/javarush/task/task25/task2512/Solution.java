package com.javarush.task.task25.task2512;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (t != null) {
            t.interrupt();
        }
        Throwable eCause = e.getCause();
        if (eCause != null) {
            uncaughtException(t, eCause);
        }
        System.out.println(e.getClass().getName() + ": " + e.getMessage());
    }


    public static void main(String[] args) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(new Solution());
            throw  new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));

    }
}
