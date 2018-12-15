package com.javarush.task.task26.task2613;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper
{
    private static ResourceBundle res =
            ResourceBundle.getBundle(CashMachine.class.getPackage().getName()+ ".resources.common_en");
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.currency.code"));
        while (true)
        {
            String code = readString();
            if (code == null || code.length() > 3 || code.length() < 3) writeMessage("try again");
            else return code.toUpperCase();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        while (true)
        {
            try
            {
                writeMessage(String.format(res.getString("choose.denomination.and.count.format"),currencyCode));
                String str = readString();
                String[] res = str.split(" ");

                if (res.length == 2 && Integer.parseInt(res[0]) > 0 && Integer.parseInt(res[1]) > 0) return res;
               // writeMessage("Invalid input. Please try again.");
            }
            catch (NumberFormatException e)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        while(true)
        {
            try
            {
                writeMessage(res.getString("choose.operation"));
                writeMessage("[1] "+res.getString("operation.INFO")+" [2] "+
                        res.getString("operation.DEPOSIT")+" [3] "+
                        res.getString("operation.WITHDRAW")+" [4] "+
                        res.getString("operation.EXIT"));
                Integer i = Integer.parseInt(readString());
                if (i>=1 && i <= Operation.values().length)
                    return Operation.getAllowableOperationByOrdinal(i);
            }
            catch (NumberFormatException e)
            {
                writeMessage(res.getString("invalid.data"));
                continue;
            }
        }

    }

    public static String readString() throws InterruptOperationException
    {
        try
        {
            String result = bis.readLine();
            if (result.toUpperCase().equals("EXIT"))
            {
                throw new InterruptOperationException();
            }
            return result;
        }
        catch (IOException e)
        {
            return null;
        }
    }
    public static void printExitMessage()
    {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }

}
