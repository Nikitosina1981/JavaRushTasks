package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n)
    {
        if (n==1) return;
        int i = 2;
        while (i<=n)
        {
            if (n%i==0)
            {
                System.out.print(i+" ");
                recursion(n/i);
                break;
            }
            else i++;
        }
        return;
    }
    public static void main(String...args)
    {
        new Solution().recursion(220);
    }
}
