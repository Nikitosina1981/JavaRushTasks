package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Locale;

public class CashMachine
{
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";
    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ENGLISH);
        Operation op = Operation.LOGIN;
        try
        {
            do
            {
                if (op.equals(Operation.LOGIN)) CommandExecutor.execute(op);
                op = ConsoleHelper.askOperation();
                CommandExecutor.execute(op);
            }
            while (!(op == Operation.EXIT));
        }
        catch (NotEnoughMoneyException e)
        {
           // ConsoleHelper.writeMessage("Not enough banknotes for this amount.");
        }
        catch (InterruptOperationException e)
        {
            ConsoleHelper.printExitMessage();
          //  ConsoleHelper.writeMessage("See you later!");
        }

    }
}
