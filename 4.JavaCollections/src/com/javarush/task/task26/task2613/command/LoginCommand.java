package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

public class LoginCommand implements Command
{
    private String r = RESOURCE_PATH+ "login_en";
    private ResourceBundle res = ResourceBundle.getBundle(r);
    private String p = CashMachine.class.getPackage().getName()+ ".resources.verifiedCards";
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(p);

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String userCard = "";
        String userPin = "";
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (true)
        {
            userCard = ConsoleHelper.readString();
            userPin = ConsoleHelper.readString();
            if (validCreditCards.containsKey(userCard))
            {
                if (validCreditCards.getString(userCard).equals(userPin))
                {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),userCard));
                    break;
                }
            }
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"),userCard));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
        }
    }
}
