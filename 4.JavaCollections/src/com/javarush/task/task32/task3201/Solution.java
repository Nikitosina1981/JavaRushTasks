package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {

        try
        {
            RandomAccessFile rac = new RandomAccessFile(args[0], "w");
            long size = rac.length();
            long target = Long.parseLong(args[1]);
            String text = args[2];
            if (size>target) rac.seek(target);
            else rac.seek(size);
            rac.write(text.getBytes("UTF-8"));
            rac.close();
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
}
