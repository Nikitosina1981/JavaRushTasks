package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static boolean checkRepeat(String s)
    {
        if (s==null || s.length()<1) return false;
        if (s.length()==1) return true;
        for (int i = 0; i < s.length(); i++)
        {
            for (int j = i+1; j < s.length(); j++)
            {
                if (s.charAt(i)==s.charAt(j)) return false;
            }
        }
        return true;
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s==null || s.length()<1) return 0;
        if (s.length()==1) return 1;
        int localMax = 0;
        int totalMax = 0;
        String sub = "";
        for (int i = 0; i < s.length(); i++)
        {
            for (int j = i+1; j <= s.length(); j++)
            {
                sub = s.substring(i,j);
                if (checkRepeat(sub)) localMax = sub.length();
                else break;
            }
            if (localMax>totalMax) totalMax = localMax;
            localMax = 0;
        }
        return totalMax;
    }
}
