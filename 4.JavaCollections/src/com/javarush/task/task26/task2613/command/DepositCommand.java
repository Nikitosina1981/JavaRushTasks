package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;


class DepositCommand implements Command
{
    private String p = RESOURCE_PATH+"deposit_en";
    private ResourceBundle res = ResourceBundle.getBundle(p);
    @Override
    public void execute() throws InterruptOperationException
    {
        try
        {
            ConsoleHelper.writeMessage(res.getString("before"));
            String c = ConsoleHelper.askCurrencyCode();
            String[] b = ConsoleHelper.getValidTwoDigits(c);
            CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(c);
            cm.addAmount(Integer.parseInt(b[0]), Integer.parseInt(b[1]));
            int amount = Integer.parseInt(b[0]) * Integer.parseInt(b[1]);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, c));
        }
        catch (NumberFormatException e)
        {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
