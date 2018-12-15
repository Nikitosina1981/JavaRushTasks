package com.javarush.task.task39.task3913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        System.out.println("IPs");
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
       System.out.println(logParser.getIPsForUser("Eduard Petrovich Morozko",null, new Date()));
       System.out.println(logParser.getIPsForEvent(Event.SOLVE_TASK,null, new Date()));
       System.out.println(logParser.getIPsForStatus(Status.OK,null, new Date()));
        System.out.println("users");
       System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(null, new Date()));
        System.out.println(logParser.getNumberOfUserEvents("Eduard Petrovich Morozko", null, new Date()));
        System.out.println(logParser.getUsersForIP("127.0.0.1",null, new Date()));
        System.out.println(logParser.getLoggedUsers(null, new Date()));
        System.out.println(logParser.getDownloadedPluginUsers(null, new Date()));
        System.out.println(logParser.getWroteMessageUsers(null, new Date()));
        System.out.println(logParser.getSolvedTaskUsers(null, new Date()));
        System.out.println(logParser.getSolvedTaskUsers(null, new Date(), 15));
        System.out.println(logParser.getDoneTaskUsers(null, null));
        System.out.println(logParser.getDoneTaskUsers(null, null, 15));
        System.out.println("Dates");
        System.out.println(logParser.getDatesForUserAndEvent("Amigo", Event.DONE_TASK,null,null));
        System.out.println(logParser.getDatesWhenSomethingFailed(null,null));
        System.out.println(logParser.getDatesWhenErrorHappened(null,null));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Amigo", null,null));
        System.out.println(logParser.getDateWhenUserSolvedTask("Amigo",18,null,null));
        System.out.println(logParser.getDateWhenUserDoneTask("Amigo", 18,null,null));
        System.out.println(logParser.getDatesWhenUserWroteMessage("Amigo",null,null));
        System.out.println(logParser.getDatesWhenUserDownloadedPlugin("Amigo",null,null));
        System.out.println("Events");
        System.out.println(logParser.getNumberOfAllEvents(null,null));
        System.out.println(logParser.getAllEvents(null,null));
        System.out.println(logParser.getEventsForIP("127.0.0.1",null,null));
        System.out.println(logParser.getEventsForUser("Amigo",null,null));
        System.out.println(logParser.getFailedEvents(null,null));
        System.out.println(logParser.getErrorEvents(null,null));
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18,null,null));
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(18,null,null));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null,null));
        System.out.println(logParser.getAllDoneTasksAndTheirNumber(null,null));
        try
        {
            System.out.println("All IP: "+logParser.execute("get ip"));
            System.out.println("All users: "+logParser.execute("get user"));
            System.out.println("All date: "+logParser.execute("get date"));
            System.out.println("All event: "+logParser.execute("get event"));
            System.out.println("All status: "+logParser.execute("get status"));
            System.out.println("get ip for user = \"Amigo\": "+logParser.execute("get ip for user = \"Amigo\""));
            System.out.println("get user for event = \"WRITE_MESSAGE\": "+logParser.execute("get user for event = " +
                    "\"WRITE_MESSAGE\""));
            System.out.println("get date for status = \"ERROR\": "+logParser.execute("get date for status = " +
                    "\"ERROR\""));
            System.out.println("get status for ip = \"127.0.0.1\": "+logParser.execute("get status for ip = " +
                    "\"127.0.0.1\""));
            System.out.println("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 " +
                    "0:00:00\" and \"03.01.2014 \" + \"23:59:59\".");
            System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between " +
                    "\"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(logParser.execute(br.readLine()));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}