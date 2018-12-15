package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;
import java.util.Map;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class WithdrawCommand implements Command
{
    private String r = RESOURCE_PATH+ "withdraw_en";
    private ResourceBundle res = ResourceBundle.getBundle(r);

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator cm = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        int sum;
        Map<Integer, Integer> notes;
            while (true)
            {
                try
                {
                    ConsoleHelper.writeMessage(res.getString("specify.amount"));
                    sum = Integer.parseInt(ConsoleHelper.readString());
                    if (sum > 0)
                    {
                        if (cm.isAmountAvailable(sum))
                        {
                             notes = cm.withdrawAmount(sum);
                             notes.entrySet().forEach(e->{
                                ConsoleHelper.writeMessage("\t"+e.getKey()+" - "+e.getValue());
                             });
                            break;
                        } else ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                    }
                }
                catch (NumberFormatException e)
                {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                }
                catch (NotEnoughMoneyException e)
                {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                }
            }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"),sum,currencyCode));
    }
}
