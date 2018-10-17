package com.javarush.task.task27.task2712.ad;

public class Advertisement
{
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration)
    {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        amountPerOneDisplaying = hits > 0 ? initialAmount / hits : 0;
    }

    public void revalidate() throws UnsupportedOperationException
    {
        if (hits<1) throw new UnsupportedOperationException();
        else
        hits = hits - 1;
    }

    @Override
    public String toString()
    {
        return "<" + name + ">";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public long getAmountPerOneDisplaying()
    {
        return amountPerOneDisplaying;
    }


    public void setAmountPerOneDisplaying(long amountPerOneDisplaying)
    {
        this.amountPerOneDisplaying = amountPerOneDisplaying;
    }

    public int getHits()
    {
        return hits;
    }
}
