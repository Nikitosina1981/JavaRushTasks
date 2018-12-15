package com.javarush.task.task26.task2613;

import java.util.*;

public class CurrencyManipulatorFactory
{
    private static CurrencyManipulatorFactory mInstance;
    private static Map<String,CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
   {
       for (String key: map.keySet())
       {
           if (key.toUpperCase().equals(currencyCode.toUpperCase())) return map.get(currencyCode.toUpperCase());
       }
        map.put(currencyCode.toUpperCase(),new CurrencyManipulator(currencyCode.toUpperCase()));
        return map.get(currencyCode.toUpperCase());
   }
   private CurrencyManipulatorFactory(){}

    public static CurrencyManipulatorFactory getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new CurrencyManipulatorFactory();
        }
        return mInstance;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return new HashSet<CurrencyManipulator>(map.values());
    }
}
