package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    private View view;
    private Provider[] provider;
    private List<Vacancy> list;

    public Model(View view, Provider... provider)
    {
        if (view==null || provider==null || provider.length==0) throw new IllegalArgumentException();
        this.view = view;
        this.provider = provider;
    }
    public void selectCity(String city)
    {
        list = new ArrayList<>();
        for (Provider p: provider)
        {
            list.addAll(p.getJavaVacancies(city));
        }
        view.update(list);
    }
}
