package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            a.add(Integer.parseInt(bufferedReader.readLine()));
        }
        Collections.sort(a, Collections.reverseOrder());
        a.forEach(u -> System.out.print(u+" "));
    }
}
