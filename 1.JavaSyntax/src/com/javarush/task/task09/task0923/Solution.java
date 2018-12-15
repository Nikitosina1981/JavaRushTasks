package com.javarush.task.task09.task0923;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Гласные и согласные
*/

public class Solution {
    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String stroka = bufferedReader.readLine().replace(" ","");
        String res1="";
        String res2="";
        for (int i = 0; i < stroka.length(); i++)
        {
            char t = stroka.charAt(i);
            if (isVowel(t)) res1+=t;
            else res2+=t;
        }
        for (int i = 0; i < res1.length(); i++)
        {
            System.out.print(res1.charAt(i)+" ");
        }
        System.out.println("");
        for (int i = 0; i < res2.length(); i++)
        {
            System.out.print(res2.charAt(i)+" ");
        }
    }

    // метод проверяет, гласная ли буква
    public static boolean isVowel(char c) {
        c = Character.toLowerCase(c);  // приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   // ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}