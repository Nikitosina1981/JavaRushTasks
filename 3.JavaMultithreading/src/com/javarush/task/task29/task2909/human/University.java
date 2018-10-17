package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class University
{
    public List<Student> getStudents()
    {
        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    private List<Student> students = new ArrayList();

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

    private int age;
    private String name;

    public University(String name, int age)
    {

    }

    public Student getStudentWithAverageGrade(double value)
    {
        //TODO:
        for (int i = 0; i < students.size(); i++)
            if (students.get(i).getAverageGrade() == value) return students.get(i);
        return null;
    }

    public Student getStudentWithMaxAverageGrade()
    {
        //TODO:
        int maxid = 0;
        double maxavg = 0;
        for (int i = 0; i < students.size(); i++)
        {
            if (students.get(i).getAverageGrade() > maxavg)
            {
                maxid = i;
                maxavg = students.get(i).getAverageGrade();
            }
        }
        return students.get(maxid);
    }

    public Student getStudentWithMinAverageGrade()
    {
        int maxid = 0;
        double maxavg = Double.MAX_VALUE;
        for (int i = 0; i < students.size(); i++)
        {
            if (students.get(i).getAverageGrade() < maxavg)
            {
                maxid = i;
                maxavg = students.get(i).getAverageGrade();
            }
        }
        return students.get(maxid);
    }

    public void expel(Student student)
    {
     List<Student> st= getStudents();
     st.remove(student);
     setStudents(st);
    }

}