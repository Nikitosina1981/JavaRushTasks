package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/* 
Добрая Зинаида и летние каникулы
*/

public class Solution
{
    public static HashMap<String, Date> createMap() throws ParseException
    {
        DateFormat df = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", df.parse("JUNE 1 1980"));
        map.put("Stallon", df.parse("JUNE 1 1980"));
        map.put("Stallo", df.parse("JUNE 1 1980"));
        map.put("Stall", df.parse("JUNE 1 1980"));
        map.put("Stal", df.parse("JUNE 1 1980"));
        map.put("Sta", df.parse("JUNE 1 1980"));
        map.put("St", df.parse("JUNE 1 1980"));
        map.put("S", df.parse("JUNE 1 1980"));
        map.put("Stallone1", df.parse("DECEMBER 1 1980"));
        map.put("Stallone2", df.parse("JANUARY 1 1980"));
        //напишите тут ваш код
        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        //напишите тут ваш код
        Iterator iterator = map.entrySet().iterator();
        Date date;
        while (iterator.hasNext())
        {
            Map.Entry pair = (Map.Entry) iterator.next();
            date = (Date) pair.getValue();
            if (date.getMonth() >= 5 && date.getMonth() <= 7)
                iterator.remove();
        }
    }
        public static void main (String[]args)
        {
    }
    }
