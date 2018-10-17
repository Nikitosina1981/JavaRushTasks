package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant
{
    private static List<Tablet> tablets = new ArrayList<>();
    final private static int ORDER_CREATING_INTERVAL = 100;
    final private static LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<Order>();

    public static LinkedBlockingQueue<Order> getOrderQueue()
    {
        return orderQueue;
    }

    public static List<Tablet> getTablets()
    {
        return tablets;
    }

    public static void main (String[] args)
    {
        for (int a=1;a<6;a++)
        {
            Tablet tablet = new Tablet(a);
            tablets.add(tablet);
        }
        Cook cook = new Cook("Amigo");
        cook.setQueue(getOrderQueue());
        Cook cook2 = new Cook("Jabba");
        cook2.setQueue(getOrderQueue());
        StatisticManager.getInstance().register(cook);
        StatisticManager.getInstance().register(cook2);

        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        cook2.addObserver(waiter);
        Thread cookThread = new Thread(cook);
        Thread cook1Thread = new Thread(cook2);
        cookThread.setDaemon(true);
        cook1Thread.setDaemon(true);
        cook1Thread.start();
        cookThread.start();


       //tablet.createOrder();
       //tablet.createOrder();
     //   tablet.createOrder();
     //   tablet.createOrder();

        Thread randomOrderThread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        randomOrderThread.start();
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
        }
        randomOrderThread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}
