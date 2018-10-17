package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable
{
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<Order>();
    private String name;
    private boolean busy = false;

    public boolean isBusy()
    {
        return busy;
    }

    public Cook(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return name;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    public void startCookingOrder(Order order)
    {
        busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order + " cooking time " + order.getTotalCookingTime() + "min");
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(), this
                .toString(), order.getTotalCookingTime() * 60, order.getDishes()));
        try
        {
            Thread.sleep(order.getTotalCookingTime() * 10);
        }
        catch (InterruptedException e)
        {
            ConsoleHelper.writeMessage("I cannot cook");
        }
        busy = false;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                ConsoleHelper.writeMessage("Orders cannot wait");
            }
            if (queue.size() > 0&& !this.isBusy())
            {
                Order order = null;
                try
                {
                    order = queue.take();
                    if (!order.isEmpty()) this.startCookingOrder(order);
                }
                catch (InterruptedException e)
                {
                }
            }
        }
    }
}
