package com.javarush.task.task39.task3913;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Stream;

public class Helper
{
    private HashMap<Integer, Item> itemBase;
    private static Helper INSTANCE;

    private Helper(Path logPath)
    {
        itemBase = parseStrings(logPath);
    }
    public static Helper getHelper(Path logPath)
    {
        if (INSTANCE==null) INSTANCE = new Helper(logPath);
        return INSTANCE;
    }

    public HashMap<Integer, Item> getItemBase()
    {
        return itemBase;
    }

    public HashMap<Integer, Item> parseStrings(Path path)
    {
        HashMap<Integer,Item> result = new HashMap<>();
        try (Stream<Path> paths = Files.walk(path)) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(a->{
                        if (a.toString().endsWith(".log"))
                        {
                            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(a.toFile())))
                            {
                                String line;
                                while ((line = bufferedReader.readLine()) != null)
                                {
                                    String[] parts = line.split("\t");
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.M.y H:m:s");
                                    Date date = simpleDateFormat.parse(parts[2]);
                                    result.put(result.size(), new Item(parts[0],parts[1],date,parts[3],
                                            toStatus(parts[4])));
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkDates(Item item,Date after, Date before)
    {
        if (before == null)
        {
            if (after==null) return true;
            else return item.getDate().getTime()>=after.getTime();
        }
        if (after==null && item.getDate().getTime()<=before.getTime()) return true;
        else if(after!=null)
            return (item.getDate().getTime()>=after.getTime() && item.getDate().getTime()<=before.getTime());
        return false;
    }
    public boolean checkDates(Item item,Date after, Date before, boolean flag)
    {
        if (before == null)
        {
            if (after==null) return true;
            else return item.getDate().getTime()>after.getTime();
        }
        if (after==null && item.getDate().getTime()<before.getTime()) return true;
        else if(after!=null)
            return (item.getDate().getTime()>after.getTime() && item.getDate().getTime()<before.getTime());
        return false;
    }

    public Event toEvent(String string)
    {
        for (Event e: Event.values())
        {
            if (string.startsWith(e.toString())) return e;
        }
        return null;
    }
    public Status toStatus(String string)
    {
        for (Status s: Status.values())
        {
            if (string.startsWith(s.toString())) return s;
        }
        return null;
    }
    public Date toDate (String string) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.M.y H:m:s");
        Date date1 = simpleDateFormat.parse(string);
        return date1;
    }
}
