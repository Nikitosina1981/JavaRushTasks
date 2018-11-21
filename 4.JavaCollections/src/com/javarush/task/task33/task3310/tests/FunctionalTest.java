package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest
{
    public void testStorage(Shortener shortener)
    {
        String r =  Helper.generateRandomString();
        Long i1 = shortener.getId(r);
        Long i2 = shortener.getId(Helper.generateRandomString());
        Long i3 = shortener.getId(r);
        String s1 = shortener.getString(i1);
        String s2 = shortener.getString(i2);
        String s3 = shortener.getString(i3);
        Assert.assertNotEquals(i2, i3);
        Assert.assertNotEquals(i2, i1);
        Assert.assertEquals(i1, i3);
    }

    @Test
    public void testHashMapStorageStrategy()
    {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy()
    {
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy()
    {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy()
    {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy()
    {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy()
    {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}
