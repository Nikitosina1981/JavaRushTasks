package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;


public class Order
{
    private final Tablet tablet;
    protected List<Dish> dishes;
    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
       initDishes();
    }

    public int getTotalCookingTime()
    {
        int res = 0;
        for (int i=0; i<dishes.size();i++)
        {
            res += dishes.get(i).getDuration();
        }
        return res;
    }

    public Tablet getTablet()
    {
        return tablet;
    }

    public List<Dish> getDishes()
    {
        return dishes;
    }

    public boolean isEmpty()
    {
        if (dishes.size()>0) return false;
        else return true;
    }

    public String toString()
    {
        if (dishes.size()>0) return "Your order: "+dishes.toString()+" of "+ tablet.toString();
        else return "";
    }
    protected void initDishes() throws IOException {
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
