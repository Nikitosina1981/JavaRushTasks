package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.storage.Storage;
import sun.misc.LRUCache;

public class CachingProxyRetriever implements Retriever
{
    OriginalRetriever originalRetriever;
    LRUCache<Long, Object> lruCache;

    public CachingProxyRetriever(Storage storage)
    {
        originalRetriever = new OriginalRetriever(storage);


    }


    @Override
    public Object retrieve(long id)
    {
        return null;
    }
}
