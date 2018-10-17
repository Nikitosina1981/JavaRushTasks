package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.*;

public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    private static class Handler extends Thread
    {
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            String u = null;
            ConsoleHelper.writeMessage("Connecting to "+ socket.getRemoteSocketAddress());
            try
            {
                Connection connection = new Connection(socket);
                u = serverHandshake(connection);
                connectionMap.put(u, connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, u));
                sendListOfUsers(connection, u);
                serverMainLoop(connection, u);
            }
            catch (IOException | ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage("Произошла ошибка");
            }
            finally
            {
                if (u!=null)
                {
                    connectionMap.remove(u);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, u));
                    ConsoleHelper.writeMessage("Чат покинул: "+u);
                }

            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException
        {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();

                if (answer.getType() == MessageType.USER_NAME) {

                    if (!answer.getData().isEmpty()) {
                        if (!connectionMap.containsKey(answer.getData())) {
                            connectionMap.put(answer.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return answer.getData();
                        }
                    }
                }
            }
        }
        private void sendListOfUsers(Connection connection, String userName) throws IOException
        {
            connectionMap.forEach((key, value) -> {
                if (!key.equals(userName))
                {
                    try
                    {
                        connection.send(new Message(MessageType.USER_ADDED, key));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message message = connection.receive();
                if (message != null && message.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                } else {
                    ConsoleHelper.writeMessage("Error!");
                }
            }


        }

    }

    public static void sendBroadcastMessage(Message message)
    {
        connectionMap.forEach((key, value) ->
        {
            try
            {
                value.send(message);
            }
            catch (IOException e)
            {
                System.out.println("Ошибка отправки на "+key);
            }
        });
    }
    public static void main (String[] args)
    {
        ServerSocket a = null;
        Socket b = null;
       System.out.println("Введите порт: ");
        try
        {
            a = new java.net.ServerSocket(ConsoleHelper.readInt());
            System.out.println("Сервер запущен");
            while (true)
            {
                b = a.accept();
                Handler h = new Handler(b);
                h.start();
            }
        }
        catch (Exception e)
        {
            try
            {
                a.close();
                b.close();
            }
            catch (IOException e1)
            {
              
            }

        }



    }
}
