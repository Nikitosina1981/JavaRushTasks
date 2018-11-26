package com.javarush.task.task39.task3913;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    public static HashMap<Integer, String> parseStrings(Path path)
    {
        HashMap<Integer,String> result = new HashMap<>();
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
                                    result.put(line.hashCode(), line);
                                }
                            }
                            catch (FileNotFoundException e)
                            {
                                e.printStackTrace();
                            }
                            catch (IOException e)
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

    public static HashMap<Integer, String> trimByDates(HashMap<Integer, String> map, Date before, Date after)
    {
        HashMap<Integer,String> result = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.M.y H:m:s");
        map.entrySet().forEach(e->{
            try
            {
                Date date = simpleDateFormat.parse(getElement(e.getValue(),2));
                if (before ==null)
                {
                    if (after==null)result.put(e.getKey(),e.getValue());
                    else if (date.getTime()>=after.getTime()) result.put(e.getKey(),e.getValue());
                }
                else if (after==null && date.getTime()<=before.getTime()) result.put(e.getKey(),e.getValue());
                else if (before!=null && after!=null)
                    if (after.getTime()<=date.getTime() && before.getTime()>=date.getTime()) result.put(e.getKey(),
                    e.getValue());
            }
            catch (ParseException e1)
            {
                e1.printStackTrace();
            }
        });
        return result;
    }

    public static String getElement(String line, int element)
    {
        String[] parts = line.split("\t");
        return parts[element];
    }
}
