package com.javarush.task.task27.task2712.kitchen;


import java.util.StringJoiner;

public enum Dish
{
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);

    private int duration;

    Dish(int i) {duration = i;}

    public int getDuration()
    {
        return duration;
    }
    public static String allDishesToString()
    {
        StringJoiner sb = new StringJoiner(" ");
        for (int i = 0; i<Dish.values().length; i++)
        {
            sb.add(Dish.values()[i].toString());
        }
        return sb.toString();
    }
}

