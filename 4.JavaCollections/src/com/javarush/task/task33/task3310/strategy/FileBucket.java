package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() // он должен возвращать размер файла на который указывает path.
    {
            try
            {
                return Files.size(path);
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
            return 0;
    }
        public void putEntry(Entry entry) //- должен сериализовывать переданный entry в файл. Учти, каждый entry
    // может содержать еще один entry.
        {
            try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path)))
            {
                objectOutputStream.writeObject(entry);
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
        }
    public Entry getEntry() {
        Entry entry = null;

        if (getFileSize() <= 0)
            return entry;

        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
            entry = (Entry) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entry;
    }
        public void remove() //- удалять файл на который указывает path.
        {
            try
            {
                Files.delete(path);
            }
            catch (IOException e)
            {
                ExceptionHandler.log(e);
            }
        }

}

