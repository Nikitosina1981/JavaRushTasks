package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable
{

    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map)
    {
        this.map = map;
    }

    public void run()
    {
        AtomicInteger i = new AtomicInteger(1);
        Thread currentThread = Thread.currentThread();
        while (!currentThread.isInterrupted())
        {
            try
            {
                map.put(String.valueOf(i), "Some text for " + i.getAndAdd(1));
                currentThread.sleep(500);
            }
            catch (InterruptedException e)
            {
                System.out.println(currentThread.getName() + " thread was terminated");
            }
        }
    }
}
