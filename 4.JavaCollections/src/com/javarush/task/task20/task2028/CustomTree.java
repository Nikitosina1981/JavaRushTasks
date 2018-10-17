package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable
{
    Entry<String> root;

    public CustomTree()
    {
        this.root = new Entry<>("root");
    }

    static class Entry<T> implements Serializable
    {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName)
        {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public void checkChildren()
        {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren()
        {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

    @Override
    public String get(int index)
    {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o)
    {
        if (o instanceof String)
        {
            String s = (String) o;
            Queue<Entry<String>> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty())
            {
                Entry<String> currentNode = queue.poll();
                if (currentNode.leftChild != null)
                {
                    if (currentNode.leftChild.elementName.equals(s))
                    {
                        currentNode.leftChild = null;
                        currentNode.availableToAddLeftChildren = true;
                        return true;
                    }
                    queue.offer(currentNode.leftChild);
                }
                if (currentNode.rightChild != null)
                {
                    if (currentNode.rightChild.elementName.equals(s))
                    {
                        currentNode.rightChild = null;
                        currentNode.availableToAddRightChildren = true;
                        return true;
                    }
                    queue.offer(currentNode.rightChild);
                }
            }
        }
        else throw new UnsupportedOperationException();
        return false;
    }

    @Override
    public int size()
    {
        int result = -1;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            Entry<String> currentNode = queue.poll();
            result++;
            if (currentNode.leftChild != null)
            {
                queue.offer(currentNode.leftChild);
            }
            if (currentNode.rightChild != null)
            {
                queue.offer(currentNode.rightChild);
            }
        }
        return result;

    }

    public String getParent(String s)
    {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            Entry<String> currentNode = queue.poll();
            if (currentNode.elementName.equals(s)) return currentNode.parent.elementName;
            else
            {
                if (currentNode.leftChild != null) queue.offer(currentNode.leftChild);
                if (currentNode.rightChild != null) queue.offer(currentNode.rightChild);
            }
        }
        return null;
    }

    public String set(int index, String element)
    {
        throw new UnsupportedOperationException();
    }

    public void add(int index, String element) {throw new UnsupportedOperationException();}

    public boolean add(String s)
    {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            Entry<String> currentNode = queue.poll();
            currentNode.checkChildren();
            if (currentNode.isAvailableToAddChildren())
            {
                if (currentNode.availableToAddLeftChildren)
                {
                    currentNode.leftChild = new Entry<>(s);
                    currentNode.leftChild.parent = currentNode;
                    return true;
                } else if (currentNode.availableToAddRightChildren)
                {
                    currentNode.rightChild = new Entry<>(s);
                    currentNode.rightChild.parent = currentNode;
                    return true;
                }
            } else
            {
                if (currentNode.leftChild != null)
                {
                    queue.offer(currentNode.leftChild);
                }
                if (currentNode.rightChild != null)
                {
                    queue.offer(currentNode.rightChild);
                }
            }
        }
        return false;
    }

    public String remove(int index) {throw new UnsupportedOperationException();}

    public List<String> subList(int fromIndex, int toIndex) {throw new UnsupportedOperationException();}

    protected void removeRange(int fromIndex, int toIndex) {throw new UnsupportedOperationException();}

    public boolean addAll(int index, Collection<? extends String> c) {throw new UnsupportedOperationException();}
}
