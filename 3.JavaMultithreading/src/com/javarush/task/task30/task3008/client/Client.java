package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client
{
    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args)
    {
        Client client = new Client();
        client.run();
    }

    public class SocketThread extends Thread
    {
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
        }
        protected void informAboutAddingNewUser(String userName)
        {
            ConsoleHelper.writeMessage("Пользователь "+userName+" подключился к чату");
        }
        protected void informAboutDeletingNewUser(String userName)
        {
            ConsoleHelper.writeMessage("Пользователь "+userName+" покинул чат");
        }
        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this)
            {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException
        {
            while(!clientConnected)
            {
                if (connection.receive().getType()==MessageType.NAME_REQUEST) connection.send(new Message(MessageType.USER_NAME, getUserName()));
                else
                if (connection.receive().getType() == MessageType.NAME_ACCEPTED) notifyConnectionStatusChanged(true);
                else throw new IOException("Unexpected MessageType");
            }
        }
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message mess = connection.receive();
                if (mess.getType() == MessageType.TEXT) processIncomingMessage(mess.getData());
                else if (mess.getType() == MessageType.USER_ADDED) informAboutAddingNewUser(mess.getData());
                else if (mess.getType() == MessageType.USER_REMOVED) informAboutDeletingNewUser(mess.getData());
                else throw new IOException("Unexpected MessageType");
            }
        }

        public void run()
        {
            String a = getServerAddress();
            int port = getServerPort();
            Socket socket = null;
            try
            {
                socket = new Socket(a,port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (ClassNotFoundException | IOException e)
            {
                notifyConnectionStatusChanged(false);
            }
        }
    }

    protected String getServerAddress()
    {
        ConsoleHelper.writeMessage("Введите адрес сервера или localhost: ");
        String address = ConsoleHelper.readString();
        return address;
    }
    protected int getServerPort()
    {
        ConsoleHelper.writeMessage("Введите порт сервера: ");
        int address = ConsoleHelper.readInt();
        return address;
    }
    protected String getUserName()
    {
        ConsoleHelper.writeMessage("Введите имя: ");
        String address = ConsoleHelper.readString();
        return address;
    }
    protected boolean shouldSendTextFromConsole()
    {
        return true;
    }
    protected SocketThread getSocketThread()
    {
        return new SocketThread();
    }
    protected void sendTextMessage(String text)
    {
        try
        {
            connection.send(new Message(MessageType.TEXT, text));
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Ошибка отправки из-за соединения");
            clientConnected = false;
        }
    }

    public void run()
    {
        SocketThread st = getSocketThread();
        st.setDaemon(true);
        st.start();
        synchronized (this)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                ConsoleHelper.writeMessage("Всё пропало...");
            }
        }
            if (clientConnected==true)
            {
                ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
                while (clientConnected)
                {
                    String a = ConsoleHelper.readString();
                    if (a.equals("exit")) break;
                    if (shouldSendTextFromConsole()) sendTextMessage(a);
                }
            }
            else
            {
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            }
        }

}
