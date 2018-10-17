package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();
    private boolean isPartOfName = false;
    private boolean isPartOfContent = false;
    private boolean isMinSize = false;
    private boolean isMaxSize = false;

    public SearchFileVisitor() {
        this.partOfName = null;
        this.partOfContent = null;
        this.minSize = 0;
        this.maxSize = 0;

        this.isPartOfContent = false;
        this.isMaxSize = false;
        this.isMinSize = false;
        this.isPartOfName = false;
    }

    public List<Path> getFoundFiles()
    {
        return foundFiles;
    }

    public void setPartOfName(String partOfName)
    {
        this.partOfName = partOfName;
        isPartOfName = true;
    }

    public void setPartOfContent(String partOfContent)
    {
        this.partOfContent = partOfContent;
        isPartOfContent = true;
    }

    public void setMinSize(int minSize)
    {
        this.minSize = minSize;
        isMinSize = true;
    }

    public void setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
        isMaxSize = true;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!attrs.isRegularFile()) return CONTINUE;

        if (isPartOfName && file.getFileName().toString().indexOf(this.partOfName) == -1)
            return CONTINUE;

        if (isMinSize && attrs.size() < minSize)
            return CONTINUE;

        if (isMaxSize && attrs.size() > maxSize)
            return CONTINUE;

        if (isPartOfContent && new String(Files.readAllBytes(file)).indexOf(partOfContent) == -1)
            return CONTINUE;

        foundFiles.add(file);

        return CONTINUE;
    }
}
