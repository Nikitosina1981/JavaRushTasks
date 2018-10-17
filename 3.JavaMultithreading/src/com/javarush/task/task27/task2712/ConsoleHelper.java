package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message)
    {
        System.out.println(message);
    }
    public static String readString() throws IOException
    {
            return br.readLine();
    }
    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> l = new ArrayList();
        boolean flag = false;
        writeMessage("Make your order: ");
        writeMessage(Dish.allDishesToString());
        for (String a = readString(); !a.equals("exit"); a = readString())
        {
                for (Dish t : Dish.values())
                {
                    if (t.toString().equals(a))
                    {
                        l.add(t);
                        flag = true;
                        writeMessage(t.name()+" added.");
                    }
                }
                if (!flag)
                {
                    writeMessage("Please try again: ");
                }
                flag = false;

        }
        return l;
    }
}
