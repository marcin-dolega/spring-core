package pl.dolega.springcore.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.EventDao;
import pl.dolega.springcore.exceptions.NoSuchRecordException;
import pl.dolega.springcore.model.Event;
import pl.dolega.springcore.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventDaoImpl implements EventDao {

    @Autowired
    LinkedHashMap<String, Event> eventStorage;

    @Override
    public Event getEventById(long eventId) {
        return eventStorage.get("event:" + eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        List<Event> eventList = new ArrayList<>();
        List<Event> eventsByTitle = new ArrayList<>();
        eventStorage.forEach((s, event) -> eventList.add(event));
        int start = pageNum * pageSize;
        int end = pageSize * (pageNum + 1);
        for (int i = start; i < end; i++) {
            if (eventList.get(i).getTitle().equals(title)) {
                eventsByTitle.add(eventList.get(i));
            }
        }
        return eventsByTitle;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        List<Event> eventList;
        List<Event> eventsForDay = new ArrayList<>();
        eventList = eventStorage.values().stream().toList();
        int start = pageNum * pageSize;
        int end = pageSize * (pageNum + 1);

        for (int i = start; i < end; i++) {
            if (eventList.get(i).getDate() == day);
            eventsForDay.add(eventList.get(i));
        }
        return eventsForDay;
    }

    @Override
    public Event createEvent(Event event) {
        return eventStorage.put("event:" + event.getId(), event);
    }

    @Override
    public Event updateEvent(Event event) {
        eventStorage.replace("event:" + event.getId(), event);
        return event;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        if (eventStorage.get("event:" + eventId) == null) {
            try {
                throw new NoSuchRecordException("event:" + eventId + " doesn't exist");
            } catch (NoSuchRecordException e) {
                e.printStackTrace();
                return false;
            }
        }
        eventStorage.remove("event:" + eventId);
        return true;
    }

    public static Date getDateWithoutTime(Date date)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy");
        return formatter.parse(formatter.format(date));
    }
}
