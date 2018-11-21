package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest
{
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids)
    {
        Date start = new Date();
        for (String s:strings)
        {
            ids.add(shortener.getId(s));
        }
        Date end = new Date();
        return end.getTime()-start.getTime();
    }

    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings)
    {
        Date start = new Date();
        for (Long s:ids)
        {
            strings.add(shortener.getString(s));
        }
        Date end = new Date();
        return end.getTime()-start.getTime();
    }

    public void testHashMapStorage()
    {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++)
        {
            origStrings.add(Helper.generateRandomString());
        }
        HashSet<Long> ids1 = new HashSet<>();
        HashSet<Long> ids2 = new HashSet<>();
        HashSet<String> strings1 = new HashSet<>();
        HashSet<String> strings2 = new HashSet<>();
        Long time1 = getTimeForGettingIds(shortener1, origStrings, ids1);
        Long time2 = getTimeForGettingIds(shortener2, origStrings, ids2);
       // Assert.assertEquals(time1,time2,30);
        Long time3 = getTimeForGettingStrings(shortener1, ids1, strings1);
        Long time4 = getTimeForGettingStrings(shortener2, ids2, strings2);
        Assert.assertTrue(time1>time2);
        Assert.assertEquals(time3,time4,30);
    }
}
