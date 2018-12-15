package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery
{
    private Path logDir;
    private Helper helper;
    private HashMap<Integer, Item> itemBase;

    public LogParser(Path logDir)
    {
        this.logDir = logDir;
        helper = Helper.getHelper(logDir);
        itemBase = helper.getItemBase();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if (helper.checkDates(e, after, before)) IPs.add(e.getIP());
        });
        return IPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getName().equals(user)) IPs.add(e.getIP());
        });
        return IPs;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(event.toString())) IPs.add(e.getIP());
        });
        return IPs;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getStatus().equals(status)) IPs.add(e.getIP());
        });
        return IPs;
    }

    @Override
    public Set<String> getAllUsers()
    {
        Set<String> IPs = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            IPs.add(e.getName());
        });
        return IPs;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if (helper.checkDates(e, after, before)) IPs.add(e.getName());
        });
        return IPs.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getName().equals(user)) IPs.add(e.getIP());
        });
        return IPs.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getIP().equals(ip)) IPs.add(e.getName());
        });
        return IPs;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        Set<String> IPs = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().equals(Event.LOGIN.toString()))
                IPs.add(e.getIP());
        });
        return IPs;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().equals(Event.DOWNLOAD_PLUGIN.toString()))
                set.add(e.getName());
        });
        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().equals(Event.WRITE_MESSAGE.toString()) &&
                    e.getStatus().equals(Status.OK)) set.add(e.getName());
        });
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(Event.SOLVE_TASK.toString()))
                set.add(e.getName());
        });
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        Set<String> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(Event.SOLVE_TASK.toString()))
                if (e.getEvent().endsWith(String.valueOf(task))) set.add(e.getName());
        });
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        Set<String> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(Event.DONE_TASK.toString()))
                set.add(e.getName());
        });
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        Set<String> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(Event.DONE_TASK.toString()))
                if (e.getEvent().endsWith(String.valueOf(task))) set.add(e.getName());
        });
        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before)
    {
        Set<Date> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(event.toString()) &&
                    e.getName().equals(user)) set.add(e.getDate());
        });
        return set;

    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before)
    {
        Set<Date> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getStatus().equals(Status.FAILED))
                set.add(e.getDate());
        });
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before)
    {
        Set<Date> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getStatus().equals(Status.ERROR))
                set.add(e.getDate());
        });
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before)
    {
        Set<Date> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getName().equals(user) &&
                    e.getEvent().equals(Event.LOGIN.toString()))
                set.add(e.getDate());
        });
        if (set.size() < 1) return null;
        return Collections.min(set);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before)
    {
        Set<Date> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(Event.SOLVE_TASK.toString()) &&
                    e.getName().equals(user)) if (e.getEvent().endsWith(String.valueOf(task)))
                set.add(e.getDate());
        });
        if (set.size() < 1) return null;
        return Collections.min(set);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before)
    {
        Set<Date> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(Event.DONE_TASK.toString()) &&
                    e.getName().equals(user))
                if ((e.getEvent().endsWith(String.valueOf(task)))) set.add(e.getDate());
        });
        if (set.size() < 1) return null;
        return Collections.min(set);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before)
    {
        Set<Date> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(Event.WRITE_MESSAGE.toString()) &&
                    e.getName().equals(user)) set.add(e.getDate());
        });
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before)
    {
        Set<Date> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getEvent().startsWith(Event.DOWNLOAD_PLUGIN.toString()) &&
                    e.getName().equals(user)) set.add(e.getDate());
        });
        return set;
    }

    public Set<Date> getAllDates(Date after, Date before)
    {
        Set<Date> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before))) set.add(e.getDate());
        });
        return set;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before)
    {
        Set<Event> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if (helper.checkDates(e, after, before))
                set.add(helper.toEvent(e.getEvent()));
        });
        return set.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before)
    {
        Set<Event> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if (helper.checkDates(e, after, before)) set.add(helper.toEvent(e.getEvent()));
        });
        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before)
    {
        Set<Event> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) &&
                    e.getIP().equals(ip)) set.add(helper.toEvent(e.getEvent()));
        });
        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before)
    {
        Set<Event> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) && e.getName().equals(user))
                set.add(helper.toEvent(e.getEvent()));
        });
        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before)
    {
        Set<Event> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) &&
                    e.getStatus().equals(Status.FAILED)) set.add(helper.toEvent(e.getEvent()));
        });
        return set;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before)
    {
        Set<Event> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) &&
                    e.getStatus().equals(Status.ERROR)) set.add(helper.toEvent(e.getEvent()));
        });
        return set;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before)
    {
        ArrayList<Integer> a = new ArrayList<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) &&
                    e.getEvent().startsWith(Event.SOLVE_TASK.toString()))
                if (e.getEvent().endsWith(String.valueOf(task))) a.add(1);
        });
        return a.size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before)
    {
        ArrayList<Integer> a = new ArrayList<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) &&
                    e.getEvent().startsWith(Event.DONE_TASK.toString()))
                if (e.getEvent().endsWith(String.valueOf(task))) a.add(1);
        });
        return a.size();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) &&
                    e.getEvent().startsWith(Event.SOLVE_TASK.toString()))
            {
                String a = e.getEvent();
                int b = Integer.parseInt(a.substring(a.lastIndexOf(" ")).trim());
                if (map.containsKey(b)) map.put(b, map.get(b) + 1);
                else map.put(b, 1);
            }
        });
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        itemBase.values().forEach(e ->
        {
            if ((helper.checkDates(e, after, before)) &&
                    e.getEvent().startsWith(Event.DONE_TASK.toString()))
            {
                String a = e.getEvent();
                int b = Integer.parseInt(a.substring(a.lastIndexOf(" ")).trim());
                if (map.containsKey(b)) map.put(b, map.get(b) + 1);
                else map.put(b, 1);
            }
        });
        return map;
    }

    public Set<Status> getAllStatus(Date after, Date before)
    {
        Set<Status> set = new HashSet<>();
        itemBase.values().forEach(e ->
        {
            if (helper.checkDates(e, after, before)) set.add(e.getStatus());
        });
        return set;
    }

    @Override
    public Set<Object> execute(String query) throws ParseException
    {
        String[] words = query.split(" ");
        Set<Object> set = new HashSet<>();

        if (words.length > 1)
        {
            String field1 = words[1];
            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(query);
            final Date before,after;
            if (words[0].equals("get"))
            {
                HashSet<Item> resultBase = new HashSet<>();
                if (query.contains(" for ") && words.length > 4)
                {
                    String field2, value1;
                    field2 = words[3];
                    m.find();
                    value1 = m.group(1);
                    if (m.find()) after = helper.toDate(m.group(1));
                    else after = null;
                    if (m.find()) before = helper.toDate(m.group(1));
                    else before = null;
                    if (field2.contains("ip")) itemBase.values().forEach(e ->
                    {
                        if (e.getIP().equals(value1) && helper.checkDates(e,after,before,true)) resultBase.add(e);
                    });
                    if (field2.contains("user")) itemBase.values().forEach(e ->
                    {
                        if (e.getName().equals(value1) && helper.checkDates(e,after,before,true)) resultBase.add(e);
                    });
                    if (field2.contains("date"))
                    {
                        Date date1 = helper.toDate(value1);
                        itemBase.values().forEach(e ->
                        {
                            if (e.getDate().getTime() == date1.getTime() && helper.checkDates(e,after,before,true))
                                resultBase.add(e);
                        });
                    }
                    if (field2.contains("event")) itemBase.values().forEach(e ->
                    {
                        if (e.getEvent().startsWith(value1) && helper.checkDates(e,after,before,true)) resultBase.add(e);
                    });
                    if (field2.contains("status")) itemBase.values().forEach(e ->
                    {
                        if (e.getStatus().equals(helper.toStatus(value1)) && helper.checkDates(e,after,before,true))
                            resultBase.add(e);
                    });
                } else resultBase.addAll(itemBase.values());
            switch (words[1])
            {
                case "ip":
                    resultBase.forEach(e->{set.add(e.getIP());});
                    return set;
                case "user":
                    resultBase.forEach(e->{set.add(e.getName());});
                    return set;
                case "date":
                    resultBase.forEach(e->{set.add(e.getDate());});
                    return set;
                case "event":
                    resultBase.forEach(e->{set.add(helper.toEvent(e.getEvent()));});
                    return set;
                case "status":
                    resultBase.forEach(e->{set.add(e.getStatus());});
                    return set;
            }
        }
        }
        return null;
    }
}