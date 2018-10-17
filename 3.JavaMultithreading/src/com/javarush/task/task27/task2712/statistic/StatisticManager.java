package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticManager
{

    private static StatisticManager INSTANCE = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet();

    private StatisticManager() {}

    public static StatisticManager getInstance()
    {
        return INSTANCE;
    }

    public void register(EventDataRow data)
    {
        statisticStorage.put(data);
    }
    public void register(Cook cook)
    {
        cooks.add(cook);
    }

    public Set<Cook> getCooks()
    {
        return cooks;
    }

    public Map<Date,Long> amountPerDay()
    {
        List<EventDataRow> events = statisticStorage.get().get(EventType.SELECTED_VIDEOS);
        Map<Date, Long> values = new HashMap<>();
        events.forEach(e ->
                {
                    VideoSelectedEventDataRow vsedr = (VideoSelectedEventDataRow)e;
                 Date tmp = round2Day(e.getDate());
                 if (values.containsKey(tmp)) values.put(tmp, values.get(tmp)+vsedr.getAmount());
                 else values.put(tmp, vsedr.getAmount());
                });
        return values;
    }

    public Map<Date, Map<String, Integer>> cookLoading() {
        Map<Date, Map<String, Integer>> tempCookingMap = new TreeMap<>();

        List<EventDataRow> tempEventList = statisticStorage.get().get(EventType.COOKED_ORDER);

        for (EventDataRow eventDataRowOne : tempEventList) {
            CookedOrderEventDataRow cookedEvent = (CookedOrderEventDataRow) eventDataRowOne;
            Date date = cookedEvent.getDate();
            Map<String, Integer> cookMap = new TreeMap<>();

            for (EventDataRow eventDataRowTwo : tempEventList) {
                CookedOrderEventDataRow cookedOrderEv = (CookedOrderEventDataRow) eventDataRowTwo;
                String nameOfCook = cookedOrderEv.getCookName();
                int cookTime = cookedOrderEv.getTime();

                if (cookedEvent.getDate() == date) {
                    if (!cookMap.containsKey(nameOfCook)) {
                        cookMap.put(nameOfCook, cookTime);
                    } else {
                        int tempTime = cookMap.get(nameOfCook) + eventDataRowTwo.getTime();
                        cookMap.put(nameOfCook, tempTime);
                    }
                }
            }

            tempCookingMap.put(date, cookMap);
        }

        return tempCookingMap;
    }

    public void activeVideoSet()
    {
        List<Advertisement> ads = StatisticAdvertisementManager.getInstance().getAdList(true);
        ads.forEach(a ->{
            ConsoleHelper.writeMessage(a.getName()+" - "+a.getHits());
        });
    }

    public void archivedVideoSet()
    {
        List<Advertisement> ads = StatisticAdvertisementManager.getInstance().getAdList(false);
        ads.forEach(a ->{
            ConsoleHelper.writeMessage(a.getName());
        });
    }

    public Date round2Day(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private class StatisticStorage
    {

        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage()
        {
            for (EventType eventType : EventType.values())
            {
                storage.put(eventType, new ArrayList<>());
            }
        }

        private void put(EventDataRow data)
        {
            storage.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> get()
        {
            return storage;
        }
    }
}
