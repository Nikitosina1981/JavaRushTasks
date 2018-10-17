package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive
{
    private static int nextId = 0;
    protected int age;
    protected String name;
    private List<Human> children = new ArrayList<>();
    private int id;

    public void setBloodGroup(BloodGroup bloodGroup)
    {
        this.bloodGroup = bloodGroup;
    }

    private BloodGroup bloodGroup;
    private Size size;

    public Human(String name, int age)
    {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.age = age;
    }

    public class Size
    {
        public int height;
        public int weight;
    }



    public void printData()
    {
        System.out.println(getPosition() + ": " + name);
    }

    public String getPosition() {return "Человек";}

    public BloodGroup getBloodGroup()
    {
        return bloodGroup;
    }



    public List<Human> getChildren()
    {
        return Collections.unmodifiableList(children);
    }


    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addChild(Human child) {this.children.add(child);}

    public void removeChild(Human child) {this.children.remove(child);}


    public int getId()
    {
        return id;
    }

    public void printSize()
    {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }

    @Override
    public void live()
    {

    }
}