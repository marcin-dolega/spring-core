package pl.dolega.springcore.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.dolega.springcore.dao.EventDao;
import pl.dolega.springcore.exceptions.NoSuchRecordException;
import pl.dolega.springcore.model.Event;
import pl.dolega.springcore.model.User;
import pl.dolega.springcore.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventDaoImpl implements EventDao {

    @Autowired
    LinkedHashMap<String, Event> eventStorage;

    Utils utils = new Utils();

    @Override
    public Event getEventById(long eventId) {
        return eventStorage.get("event:" + eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        int skipCount = (pageNum - 1) * pageSize;
        return eventStorage.values()
                .stream()
                .filter(event -> event.getTitle().equals(title))
                .skip(skipCount)
                .limit(pageSize)
                .toList();
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        int skipCount = (pageNum - 1) * pageSize;
        return eventStorage.values()
                .stream()
                .filter(event -> {
                    try {
                        return getDateWithoutTime(event.getDate()).equals(day);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .skip(skipCount)
                .limit(pageSize)
                .toList();
    }

    @Override
    public Event createEvent(Event event) {
        if (utils.doesExist("event", event.getId(), eventStorage)) {
            return event;
        }
        return eventStorage.put("event:" + event.getId(), event);
    }

    @Override
    public Event updateEvent(Event event) {
        if (utils.doesntExist("event", event.getId(), eventStorage)) {
            eventStorage.put("event:" + event.getId(), event);
        }
        eventStorage.replace("event:" + event.getId(), event);
        return event;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        if (utils.doesntExist("event", eventId, eventStorage)) {
            return false;
        }
        eventStorage.remove("event:" + eventId);
        return true;
    }

    private Date getDateWithoutTime(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(formatter.format(date));
    }

}
