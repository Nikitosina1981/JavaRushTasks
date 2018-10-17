package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class DirectorTablet
{
    static double res;
    public void printAdvertisementProfit()
    {
        Map<Date, Long> values = StatisticManager.getInstance().amountPerDay();
        values.entrySet().forEach(e ->
        {
            DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String plain = formatter.format(e.getKey());
            ConsoleHelper.writeMessage(plain+" - "+ Double.valueOf(e.getValue())/100);
            double ss = e.getValue();
            res = res + ss;
        });
        ConsoleHelper.writeMessage("Total - "+res/100);
        res=0;
    }



    public void printCookWorkloading()
    {
        Map<Date, Map<String, Integer>> map = StatisticManager.getInstance().cookLoading();
        for (Map.Entry entry : map.entrySet()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String date = simpleDateFormat.format(entry.getKey());
            ConsoleHelper.writeMessage(date);
            Map<String, Integer> map1 = (Map) entry.getValue();
            for (Map.Entry entry1 : map1.entrySet()) {
                int time = (int)entry1.getValue()/60;
                ConsoleHelper.writeMessage(entry1.getKey() + " - " + time + " min");
            }
        }
    }

    public void printActiveVideoSet()
    {
        StatisticManager.getInstance().activeVideoSet();
    }

    public void printArchivedVideoSet()
    {
        StatisticManager.getInstance().archivedVideoSet();
    }

}
