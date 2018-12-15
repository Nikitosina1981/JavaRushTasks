package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        List<String> list = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++)
        {
            list.add(bufferedReader.readLine());
        }
        int smallest = Integer.MAX_VALUE;
        int longest = 0;
        for (int i = 0; i < list.size(); i++)
        {
            if (smallest>list.get(i).length()) smallest = list.get(i).length();
            if (longest<list.get(i).length()) longest = list.get(i).length();
        }
        for (int i = 0; i < list.size(); i++)
        {
            if (smallest==list.get(i).length()||longest==list.get(i).length())
            {
                System.out.println(list.get(i));
                break;
            }
        }
    }
}
