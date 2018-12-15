package com.javarush.task.task39.task3913;

import java.util.Date;

public class Item
{
    private String IP;
    private String Name;
    private Date date;
    private String event;
    private Status status;

    public Item(String IP, String name, Date date, String event, Status status)
    {
        this.IP = IP;
        Name = name;
        this.date = date;
        this.event = event;
        this.status = status;
    }

    public String getIP()
    {
        return IP;
    }

    public String getName()
    {
        return Name;
    }

    public Date getDate()
    {
        return date;
    }

    public String getEvent()
    {
        return event;
    }

    public Status getStatus()
    {
        return status;
    }
}
