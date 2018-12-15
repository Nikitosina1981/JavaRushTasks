package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();
    static boolean hasMoney = false;

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
       if (!denominations.containsKey(denomination)) denominations.put(denomination,count);
       else denominations.put(denomination, denominations.get(denomination)+count);
       hasMoney = true;
    }

    public boolean hasMoney()
    {
        return hasMoney;
    }

    public int getTotalAmount()
    {
        int result = 0;
        for (Integer num: denominations.keySet())
        {
            result = result + denominations.get(num)*num;
        }
        return result;
    }
    public boolean isAmountAvailable(int expectedAmount)
    {
        if (getTotalAmount()>=expectedAmount) return true;
        return false;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        HashMap<Integer,Integer> tmpBase= new HashMap<>(denominations);
        ArrayList<Integer> notesList = new ArrayList<>(tmpBase.keySet());
        notesList.sort(Collections.reverseOrder());
        int pointer = 0;
        Map<Integer, Integer> result = new HashMap<>();
        int expAmount = expectedAmount;
        while (expAmount>0 && pointer < notesList.size())
        {
            if (tmpBase.get(notesList.get(pointer))>0 && notesList.get(pointer)<=expAmount)
            {
                if (result.containsKey(notesList.get(pointer))) result.put(notesList.get(pointer),
                        result.get(notesList.get(pointer))+1);
                else result.put(notesList.get(pointer),1);
                tmpBase.put(notesList.get(pointer),tmpBase.get(notesList.get(pointer))-1);
                expAmount=expAmount-notesList.get(pointer);
            }
            else pointer++;
            if (pointer==notesList.size()) break;
        }
        if (expAmount>0) throw new NotEnoughMoneyException();
        tmpBase.values().removeAll(Collections.singleton(0));
        denominations.clear();
        denominations.putAll(tmpBase);

        return result;
    }

}
