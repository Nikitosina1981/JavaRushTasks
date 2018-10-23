package com.javarush.task.task37.task3707;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable, Set
{
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    @Override
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }

    public AmigoSet()
    {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        int capacity = (int) Math.max(16, collection.size() / .75f + 1);
        map = new HashMap<>(capacity);
        for (E entry : collection)
        {
            map.put(entry, PRESENT);
        }
    }

    @Override
    public Object clone()
    {
        try
        {
            AmigoSet c = (AmigoSet) super.clone();
            c.map = (HashMap) this.map.clone();
            return c;
        }
        catch (Exception e)
        {
            throw new InternalError();
        }
    }

    private void writeObject(ObjectOutputStream oos) throws Exception
    {
        oos.defaultWriteObject();
        oos.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        oos.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        oos.writeInt(map.size());
        for (E e : map.keySet()) oos.writeObject(e);
    }

    private void readObject(ObjectInputStream ois) throws Exception
    {
        ois.defaultReadObject();
        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        int size = ois.readInt();
        map = new HashMap<>(capacity,loadFactor);

        for (int i = 0; i < size; i++)
        {
            E e = (E) ois.readObject();
            map.put(e,PRESENT);
        }
    }

    @Override
    public boolean add(Object o) {
        return null==map.put((E) o,PRESENT);
    }

    @Override
    public int size()
    {
        return map.size();
    }
    public boolean isEmpty()
    {
        return this.map.isEmpty();
    }

    @Override
    public boolean remove(Object o)
    {
        return super.remove(o);
    }

    @Override
    public boolean contains(Object o)
    {
        return super.contains(o);
    }

    @Override
    public void clear()
    {
        map.clear();
    }


}
