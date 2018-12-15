package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static <T>ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        return new ArrayList<>(Arrays.asList(elements));
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        return new HashSet<>(Arrays.asList(elements));
    }

    public static <K, V>HashMap<K, V> newHashMap(List<? extends K> key, List<? extends V> value)
    {
        //напишите тут ваш код
        if (key.size() != value.size())
            throw new IllegalArgumentException();

        HashMap<K, V> res = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            res.put(key.get(i), value.get(i));
        }
        return res;
    }
}
