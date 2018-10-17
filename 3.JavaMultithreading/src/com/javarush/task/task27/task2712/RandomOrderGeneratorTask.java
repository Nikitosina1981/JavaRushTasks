package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable
{
    private static List<Tablet> tablets = Restaurant.getTablets();
    private int setOff;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval)
    {
        setOff = interval;
        this.tablets = tablets;
    }
    @Override
    public void run()
    {
        if (tablets.isEmpty()) return;
        while (!Thread.currentThread().isInterrupted())
        {
            int tab = (int) Math.round(Math.random() * (tablets.size()-1));
            tablets.get(tab).createTestOrder();
            synchronized(this)
            {
                try
                {
                    wait(setOff);
                }
                catch (InterruptedException e)
                {
                    return;
                }
            }


        }
    }
}
