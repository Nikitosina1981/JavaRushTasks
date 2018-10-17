package com.javarush.task.task31.task3107;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Null Object Pattern
1. Конструктор Solution должен инициализировать поле fileData объектом ConcreteFileData.
2. Если в конструкторе Solution возникла ошибка, нужно инициализировать поле fileData объектом NullFileData.
3. Конструктор Solution должен корректно устанавливать значение поля hidden у объекта ConcreteFileData.
4. Конструктор Solution должен корректно устанавливать значение поля executable у объекта ConcreteFileData.
5. Конструктор Solution должен корректно устанавливать значение поля directory у объекта ConcreteFileData.
6. Конструктор Solution должен корректно устанавливать значение поля writable у объекта ConcreteFileData.


*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        Path target = Paths.get(pathToFile);
        try
        {
            fileData = new ConcreteFileData(Files.isHidden(target), Files.isExecutable(target),
                    Files.isDirectory(target), Files.isWritable(target));
        }
        catch (Exception e)
        {
            fileData = new NullFileData(e);
        }

    }

    public FileData getFileData() {
        return fileData;
    }
}
