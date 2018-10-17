package com.javarush.task.task27.task2712;
import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet
{
    final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<Order>();

    @Override
    public String toString()
    {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

    public Tablet(int number)
    {
        this.number = number;
        queue = Restaurant.getOrderQueue();
    }
    public void createTestOrder()
    {
        try
        {
            TestOrder order = new TestOrder(this);
            if(!order.isEmpty())
            {
                try
                {
                    queue.put(order);
                    new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();

                }
                catch (NoVideoAvailableException e)
                {
                    logger.log(Level.INFO, "No video is available for the order " + order);
                    StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime()*60));
                }
                catch (InterruptedException e)
                {

                }
            }
        }
        catch (IOException e)
        {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }


    public Order createOrder()
    {
        Order order = null;
        try
    {
        order = new Order(this);
        ConsoleHelper.writeMessage(order.toString());
        if(!order.isEmpty())
        {
            try
            {
                queue.put(order);
                new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();

            }
            catch (NoVideoAvailableException e)
            {
                logger.log(Level.INFO, "No video is available for the order " + order);
                StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime()*60));
            }
            catch (InterruptedException e)
            {

            }
        }
    }
        catch (IOException e)
    {
        logger.log(Level.SEVERE, "Console is unavailable.");
    }
        return order;
}
}
