package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client
{
   public class BotSocketThread extends SocketThread
    {
        protected void clientMainLoop()
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            try
            {
                super.clientMainLoop();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }

        }
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
            SimpleDateFormat format = null;
            if (message.contains(": ")) {
                String[] massiv = message.split(": ");
                if (massiv.length == 2 && massiv[1] != null) {
                    String name = massiv[0];
                    String text = massiv[1];
                    switch (text) {
                        case "дата":
                            format = new SimpleDateFormat("d.MM.YYYY");
                            break;
                        case "день":
                            format = new SimpleDateFormat("d");
                            break;
                        case "месяц":
                            format = new SimpleDateFormat("MMMM");
                            break;
                        case "год":
                            format = new SimpleDateFormat("YYYY");
                            break;
                        case "время":
                            format = new SimpleDateFormat("H:mm:ss");
                            break;
                        case "час":
                            format = new SimpleDateFormat("H");
                            break;
                        case "минуты":
                            format = new SimpleDateFormat("m");
                            break;
                        case "секунды":
                            format = new SimpleDateFormat("s");
                            break;

                    }
                    if (format != null) {
                        sendTextMessage(String.format("Информация для %s: %s", name, format.format(Calendar.getInstance().getTime())));
                    }
                }
            }
        }

    }

    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    protected boolean shouldSendTextFromConsole()
    {
        return false;
    }

    protected String getUserName()
    {
        return "date_bot_" + (int)(Math.random()*100);
    }
    public static void main (String[] args)
    {
        BotClient bot = new BotClient();
        bot.run();
    }
}
