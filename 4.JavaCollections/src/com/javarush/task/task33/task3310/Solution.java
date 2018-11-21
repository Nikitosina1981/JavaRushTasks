package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution
{
    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {
        Set<Long> setup = new HashSet<>();
        for (String st:strings)
        {
            setup.add(shortener.getId(st));
        }
        return setup;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys)
    {
        Set<String> setup = new HashSet<>();
        for (Long st:keys)
        {
            setup.add(shortener.getString(st));
        }
        return setup;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++)
        {
            strings.add(Helper.generateRandomString());
        }
        Shortener newShortener = new Shortener(strategy);
        Date start1 = new Date();
        Set<Long> ids = getIds(newShortener, strings);
        Date end1 = new Date();
        long diff = end1.getTime()-start1.getTime();
        Helper.printMessage(String.valueOf(diff));
        Date start2 = new Date();
        Set<String> sts = getStrings(newShortener,ids);
        Date end2 = new Date();
        diff = end2.getTime()-start2.getTime();
        Helper.printMessage(String.valueOf(diff));
        if (strings.containsAll(sts)) Helper.printMessage("Тест пройден.");
        else Helper.printMessage("Тест не пройден.");
    }
    public static void main(String[] args)
    {
        HashMapStorageStrategy str = new HashMapStorageStrategy();
        testStrategy(str, 2000);
        FileStorageStrategy fss = new FileStorageStrategy();
        testStrategy(fss, 20);
        OurHashBiMapStorageStrategy ohbms = new OurHashBiMapStorageStrategy();
        testStrategy(ohbms, 2000);
        HashBiMapStorageStrategy hbms = new HashBiMapStorageStrategy();
        testStrategy(hbms, 2000);
        OurHashMapStorageStrategy ohms = new OurHashMapStorageStrategy();
        testStrategy(ohms, 2000);
        DualHashBidiMapStorageStrategy dhbm = new DualHashBidiMapStorageStrategy();
        testStrategy(dhbm, 2000);
    }
}
