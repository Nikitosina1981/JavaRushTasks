package com.javarush.task.task09.task0921;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Метод в try..catch
*/

public class Solution {
    public static void main(String[] args) {
        readData();
    }

    public static void readData() {
        //напишите тут ваш код
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            try
            {
                Integer a = Integer.parseInt(bufferedReader.readLine());
                list.add(a);
            }
            catch (Exception E)
            {
                list.forEach(e-> System.out.println(e));
                break;
            }
        }
    }
}
