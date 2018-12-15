package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Double> list = new ArrayList<>();
        Double a;
        while((a=Double.parseDouble(bufferedReader.readLine()))!=-1)
        {
            list.add(a);
        }
        Double sum = 0.0;
        for (Double i:list)
        {
            sum=sum+i;
        }
        sum = sum/list.size();
        System.out.println(sum);
    }
}

