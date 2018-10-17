package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/* c:\1
Что внутри папки?
*/
public class Solution
{
    static int totalFolders = 0;
    static int totalFiles = 0;
    static long totalSize = 0;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String p = br.readLine();
        Path pth = Paths.get(p);

        if (!Files.isDirectory(pth))
        {
            System.out.println(pth.toAbsolutePath().toString() + " - не папка");
            return;
        }
        else
        {
            Files.walkFileTree(pth, new Visitor());
            System.out.println("Всего папок - " + (totalFolders - 1));
            System.out.println("Всего файлов - " + totalFiles);
            System.out.println("Общий размер - " + totalSize);

        }
        br.close();
    }

    private static class Visitor extends SimpleFileVisitor<Path>
    {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
        {
            totalFolders += 1;
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
            totalFiles += 1;
            totalSize = totalSize + attrs.size();
            return CONTINUE;
        }
    }
}


