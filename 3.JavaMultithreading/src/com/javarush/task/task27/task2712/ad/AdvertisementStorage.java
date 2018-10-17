package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage
{
    private final List videos = new ArrayList();

    public List list()
    {
        return videos;
    }

    public void add(Advertisement advertisement)
    {
        videos.add(advertisement);
    }

    private AdvertisementStorage()
    {
        Object someContent = new Object();
       add (new Advertisement(someContent, "First Video", 5000, 100, 3 * 60+1));
        add( new Advertisement(someContent, "ыSecond Video", 5000, 100, 3 * 60+2));
        add (new Advertisement(someContent, "аThird Video", 5000, 100, 3 * 60+3));
        add (new Advertisement(someContent, "Forth Video", 1000, 50, 1 * 60+31));
        add( new Advertisement(someContent, "Fifth Video", 1000, 50, 1 * 60+32));
        add (new Advertisement(someContent, "Sixth Video", 200, 1, 1 * 60+33));
      /*  add (new Advertisement(someContent, "1First Video", 5000, 100, 2 * 60+1));
        add( new Advertisement(someContent, "1Second Video", 5000, 100, 2 * 60+2));
        add (new Advertisement(someContent, "1Third Video", 5000, 100, 2 * 60+3));
        add (new Advertisement(someContent, "1Forth Video", 1000, 50, 4 * 60+31));
        add( new Advertisement(someContent, "1Fifth Video", 1000, 50, 4 * 60+32));
        add (new Advertisement(someContent, "1Sixth Video", 1000, 50, 4 * 60+33));
        add(new Advertisement(someContent, "Forth Video", 2000, 40, 7 * 60));
     add(new Advertisement(someContent, "Fifth Video", 100, 9, 1 * 60));
        add(new Advertisement(someContent, "Sixth Video", 1540, 19, 4 * 60));
        add(new Advertisement(someContent, "Seventh Video", 5500, 200, 1 * 60));
        add(new Advertisement(someContent, "Eighth Video", 10000, 100, 10 * 60));
        add(new Advertisement(someContent, "Ninth Video", 100, 10, 1 * 60));
        add(new Advertisement(someContent, "Tenth Video", 1000, 50, 9 * 60));*/
    }

    public static AdvertisementStorage getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder
    {
        private static final AdvertisementStorage INSTANCE = new AdvertisementStorage();
    }
}
