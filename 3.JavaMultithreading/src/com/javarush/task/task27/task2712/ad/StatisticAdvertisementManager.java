package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.CASE_INSENSITIVE_ORDER;

public class StatisticAdvertisementManager
{
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private static StatisticAdvertisementManager INSTANCE = new StatisticAdvertisementManager();

    private StatisticAdvertisementManager() {}

    public static StatisticAdvertisementManager getInstance()
    {
        return INSTANCE;
    }

    public ArrayList<Advertisement> getAdList (boolean active)
    {
        List<Advertisement> listAds = advertisementStorage.list();
        ArrayList<Advertisement> result = new ArrayList<>();
        listAds.forEach(a->{
            if (active&&a.getHits()>0) result.add(a);
            if (!active&&a.getHits()==0) result.add(a);
        });
        Comparator<Advertisement> aaa = Comparator.comparing(Advertisement::getName, CASE_INSENSITIVE_ORDER);
        result.sort(aaa);
        return result;
    }
}
