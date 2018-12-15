package com.javarush.task.task08.task0827;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isDateOdd("MAY 2 2013"));
    }

    public static boolean isDateOdd(String date) {
        Date a = new Date(date);
        Instant instant = a.toInstant();
        LocalDate ld = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        int day = ld.getDayOfYear();
        if (day%2==0) return false;
        else return true;
    }
}
