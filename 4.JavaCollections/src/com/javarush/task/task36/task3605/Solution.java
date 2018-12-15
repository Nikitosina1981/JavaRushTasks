package com.javarush.task.task36.task3605;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String data = new String (Files.readAllBytes(Paths.get(args[0]))).toLowerCase();
        TreeSet<Character> tree = new TreeSet<>();
        for (int i = 0; i < data.length(); i++)
        {
            if (Character.isLetter(data.charAt(i))) tree.add(data.charAt(i));
        }
        for (int i = 0; i < 5; i++)
        {
            if (tree.size()==0) break;
            System.out.print(tree.pollFirst());
        }
    }
}
