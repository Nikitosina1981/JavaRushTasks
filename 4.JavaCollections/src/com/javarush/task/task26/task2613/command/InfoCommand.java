package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import java.util.Collection;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class InfoCommand implements Command
{
    private String p = RESOURCE_PATH+ "info_en";
    private ResourceBundle res = ResourceBundle.getBundle(p);
    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        Collection<CurrencyManipulator> map = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (map.size()==0) ConsoleHelper.writeMessage(res.getString("no.money"));
        else
        {
            for (CurrencyManipulator a : map)
            {
                if (a.hasMoney())
                    ConsoleHelper.writeMessage(a.getCurrencyCode() + " - " + String.valueOf(a.getTotalAmount()));
                else ConsoleHelper.writeMessage(res.getString("no.money"));
            }
        }
    }
}
