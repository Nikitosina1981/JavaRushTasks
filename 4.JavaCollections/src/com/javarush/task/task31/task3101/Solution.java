package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.Comparator;
import java.util.TreeSet;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {

        if (args[0]!=null)
        {
            File path = new File(args[0]);
            if (args[1]!=null)
            {
                File resultFileAbsolutePath = new File(args[1]);
                File dest = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
                FileUtils.renameFile(resultFileAbsolutePath, dest);

                try(FileOutputStream fos = new FileOutputStream(dest, true);)
                {
                    TreeSet<File> fi = new TreeSet<>(new FileNameComparator());
                    fi = findAllFiles(path, fi);

                    for (File aaa: fi)
                    {
                        try (FileInputStream fis = new FileInputStream(aaa))
                        {
                            while (fis.available()>0)
                            {
                                fos.write(fis.read());
                            }
                            fos.write('\n');
                        }
                        catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }

                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }
    public static TreeSet<File> findAllFiles(File path, TreeSet<File> f)
    {
        File[] bb = new File(String.valueOf(path)).listFiles();
        for (File u : bb)
        {
            if (u!=null)
            {
               if (u.isFile()&&u.length()<=50&&!f.contains(u)) f.add(u);
               else if (u.isDirectory()) f.addAll(findAllFiles(u, f));
            }
        }
        return f;
    }

    static class FileNameComparator implements Comparator<File>
    {
        public int compare(File first, File second)
        {
            return first.getName().compareTo(second.getName());
        }
    }
}
